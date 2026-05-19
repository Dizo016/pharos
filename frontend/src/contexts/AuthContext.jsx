import { createContext, useContext, useState, useCallback } from 'react'
import api from '../api/api'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('pharos_user')
    return saved ? JSON.parse(saved) : null
  })

  const [token, setToken] = useState(() => localStorage.getItem('pharos_token') ?? null)

  const login = useCallback(async (login, password) => {
    const { data } = await api.post('/auth/login', { login, password })
    localStorage.setItem('pharos_token', data.token)
    setToken(data.token)

    const { data: userData } = await api.get('/auth/me', {
      headers: { Authorization: `Bearer ${data.token}` },
    })
    localStorage.setItem('pharos_user', JSON.stringify(userData))
    setUser(userData)
    return userData
  }, [])

  const logout = useCallback(() => {
    localStorage.removeItem('pharos_token')
    localStorage.removeItem('pharos_user')
    setToken(null)
    setUser(null)
  }, [])

  const isAuthenticated = !!token

  return (
    <AuthContext.Provider value={{ user, token, isAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const ctx = useContext(AuthContext)
  if (!ctx) throw new Error('useAuth must be used inside <AuthProvider>')
  return ctx
}
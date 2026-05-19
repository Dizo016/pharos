import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider, useAuth } from './contexts/AuthContext'
import PrivateRoute from './components/PrivateRoute/PrivateRoute'
import Login from './components/Login/Login'
import './App.css'

function HomePlaceholder() {
  const { logout } = useAuth()
  return (
    <div className="ph-page">
      <span className="ph-tag">// Pharos — autenticado com sucesso</span>
      <p className="ph-text">Dashboard em construção.</p>
      <button className="ph-btn" onClick={logout}>logout</button>
    </div>
  )
}

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to="/login" replace />} />
          <Route path="/login" element={<Login />} />

          <Route element={<PrivateRoute />}>
            <Route path="/home" element={<HomePlaceholder />} />
          </Route>

          <Route path="*" element={<Navigate to="/login" replace />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}
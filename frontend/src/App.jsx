import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider, useAuth } from './contexts/AuthContext'
import PrivateRoute from './components/PrivateRoute/PrivateRoute'
import Landing from './components/Landing/Landing'
import Login from './components/Login/Login'
import Cadastro from './components/Cadastro/Cadastro'
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
          {/* Públicas */}
          <Route path="/"         element={<Landing />} />
          <Route path="/login"    element={<Login />} />
          <Route path="/cadastro" element={<Cadastro />} />

          {/* Protegidas */}
          <Route element={<PrivateRoute />}>
            <Route path="/home" element={<HomePlaceholder />} />
          </Route>

          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}
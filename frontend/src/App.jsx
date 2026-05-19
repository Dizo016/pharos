import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider } from './contexts/AuthContext'
import PrivateRoute from './components/PrivateRoute/PrivateRoute'
import Landing from './components/Landing/Landing'
import Login from './components/Login/Login'
import Cadastro from './components/Cadastro/Cadastro'
import Home from './components/Home/Home'
import './App.css'

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          {/* Públicas */}
          <Route path="/"         element={<Landing />} />
          <Route path="/login"    element={<Login />} />
          <Route path="/cadastro" element={<Cadastro />} />
          <Route path="/explorar" element={<div style={{ color: 'var(--amber)', padding: 48, fontFamily: 'JetBrains Mono, monospace' }}>// explorar — em breve</div>} />

          {/* Protegidas — Home renderiza sidebar + conteúdo via Outlet */}
          <Route element={<PrivateRoute />}>
            <Route path="/home"    element={<Home />} />
            <Route path="/acervo"  element={<Home />} />
            <Route path="/membros" element={<Home />} />
            <Route path="/config"  element={<Home />} />
          </Route>

          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}
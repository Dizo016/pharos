import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../../api/api'
import './Cadastro.css'

export default function Cadastro() {
  const navigate = useNavigate()

  const [form, setForm] = useState({ name: '', login: '', password: '', confirm: '' })
  const [showPass, setShowPass]       = useState(false)
  const [showConfirm, setShowConfirm] = useState(false)
  const [loading, setLoading]         = useState(false)
  const [error, setError]             = useState('')
  const [success, setSuccess]         = useState(false)

  function handleChange(e) {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }))
    setError('')
  }

  async function handleSubmit(e) {
    e.preventDefault()

    if (!form.name.trim())     { setError('// nome não pode estar vazio');  return }
    if (!form.login.trim())    { setError('// login não pode estar vazio'); return }
    if (form.password.length < 6) { setError('// senha deve ter no mínimo 6 caracteres'); return }
    if (form.password !== form.confirm) { setError('// as senhas não coincidem'); return }

    setLoading(true)
    setError('')

    try {
      await api.post('/users', {
        name:     form.name.trim(),
        login:    form.login.trim(),
        password: form.password,
        userRole: 'LIBRARIAN',
      })
      setSuccess(true)
    } catch (err) {
      const status = err?.response?.status
      if (status === 409) setError('// este login já está em uso')
      else                setError('// não foi possível criar a conta. Tente novamente.')
    } finally {
      setLoading(false)
    }
  }

  if (success) {
    return (
      <div className="cad-page">
        <div className="cad-page-bg" aria-hidden="true" />
        <div className="cad-success">
          <span className="cad-success-icon">✦</span>
          <h2 className="cad-success-title">Conta criada.</h2>
          <p className="cad-success-sub">
            Seu acesso ao Pharos foi registrado como <strong>Bibliotecário</strong>.
          </p>
          <button className="cad-btn" onClick={() => navigate('/login')}>
            Ir para o login
          </button>
        </div>
      </div>
    )
  }

  return (
    <div className="cad-page">
      <div className="cad-page-bg" aria-hidden="true" />

      <div className="cad-wrapper">

        {/* Cabeçalho */}
        <div className="cad-header">
          <button className="cad-back" onClick={() => navigate('/')}>
            ← voltar
          </button>
          <span className="cad-tag">// novo acesso</span>
          <h1 className="cad-title">Criar conta</h1>
          <p className="cad-sub">
            Preencha os dados para registrar seu acesso ao sistema.
            Todas as contas criadas aqui recebem perfil de <em>Bibliotecário</em>.
          </p>
        </div>

        {/* Formulário */}
        <form className="cad-form" onSubmit={handleSubmit} noValidate>

          <div className="cad-field">
            <label htmlFor="cad-name">Nome completo</label>
            <input
              id="cad-name"
              name="name"
              type="text"
              placeholder="Seu nome"
              value={form.name}
              onChange={handleChange}
              autoFocus
            />
          </div>

          <div className="cad-field">
            <label htmlFor="cad-login">Login</label>
            <input
              id="cad-login"
              name="login"
              type="text"
              placeholder="seu.login"
              value={form.login}
              onChange={handleChange}
              autoComplete="username"
            />
          </div>

          <div className="cad-field">
            <label htmlFor="cad-pass">Senha</label>
            <div className="cad-pass-wrapper">
              <input
                id="cad-pass"
                name="password"
                type={showPass ? 'text' : 'password'}
                placeholder="mínimo 6 caracteres"
                value={form.password}
                onChange={handleChange}
                autoComplete="new-password"
              />
              <button
                type="button"
                className="cad-pass-toggle"
                onClick={() => setShowPass((s) => !s)}
                aria-label={showPass ? 'Ocultar senha' : 'Mostrar senha'}
              >
                <EyeIcon open={showPass} />
              </button>
            </div>
          </div>

          <div className="cad-field">
            <label htmlFor="cad-confirm">Confirmar senha</label>
            <div className="cad-pass-wrapper">
              <input
                id="cad-confirm"
                name="confirm"
                type={showConfirm ? 'text' : 'password'}
                placeholder="repita a senha"
                value={form.confirm}
                onChange={handleChange}
                autoComplete="new-password"
              />
              <button
                type="button"
                className="cad-pass-toggle"
                onClick={() => setShowConfirm((s) => !s)}
                aria-label={showConfirm ? 'Ocultar senha' : 'Mostrar senha'}
              >
                <EyeIcon open={showConfirm} />
              </button>
            </div>
          </div>

          {error && (
            <div className="cad-error" role="alert">
              <svg width="12" height="12" viewBox="0 0 24 24" fill="none"
                stroke="currentColor" strokeWidth="2">
                <circle cx="12" cy="12" r="10"/>
                <line x1="12" y1="8" x2="12" y2="12"/>
                <line x1="12" y1="16" x2="12.01" y2="16"/>
              </svg>
              {error}
            </div>
          )}

          <button type="submit" className="cad-btn" disabled={loading}>
            {loading
              ? <span className="cad-spinner" />
              : <span>Criar conta</span>
            }
          </button>

          <p className="cad-login-link">
            Já tem acesso?{' '}
            <button type="button" className="cad-link" onClick={() => navigate('/login')}>
              Entrar no sistema
            </button>
          </p>

        </form>
      </div>
    </div>
  )
}

function EyeIcon({ open }) {
  return open ? (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none"
      stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94"/>
      <path d="M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19"/>
      <line x1="1" y1="1" x2="23" y2="23"/>
    </svg>
  ) : (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none"
      stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
      <circle cx="12" cy="12" r="3"/>
    </svg>
  )
}
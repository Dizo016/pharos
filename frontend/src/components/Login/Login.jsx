import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../../contexts/AuthContext'
import './Login.css'

// Ícone de farol SVG inline
function LighthouseIcon() {
  return (
    <svg className="ln-lighthouse-icon" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
      <rect x="20" y="6" width="8" height="6" rx="1" fill="#c8a96e" opacity="0.9"/>
      <path d="M18 12 L14 38 L34 38 L30 12 Z" fill="none" stroke="#c8a96e" strokeWidth="1.5" opacity="0.7"/>
      <rect x="14" y="38" width="20" height="3" rx="1" fill="#c8a96e" opacity="0.5"/>
      <line x1="14" y1="22" x2="34" y2="22" stroke="#c8a96e" strokeWidth="1" opacity="0.4"/>
      <line x1="13" y1="30" x2="35" y2="30" stroke="#c8a96e" strokeWidth="1" opacity="0.4"/>
      {/* raios de luz */}
      <line x1="24" y1="6" x2="24" y2="2"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.6"/>
      <line x1="24" y1="6" x2="30" y2="3"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.4"/>
      <line x1="24" y1="6" x2="18" y2="3"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.4"/>
      <line x1="24" y1="6" x2="34" y2="5"  stroke="#c8a96e" strokeWidth="1"   opacity="0.25"/>
      <line x1="24" y1="6" x2="14" y2="5"  stroke="#c8a96e" strokeWidth="1"   opacity="0.25"/>
    </svg>
  )
}

export default function Login() {
  const { login: doLogin } = useAuth()
  const navigate = useNavigate()

  const [loginValue, setLoginValue] = useState('')
  const [password, setPassword]     = useState('')
  const [showPass, setShowPass]     = useState(false)
  const [loading, setLoading]       = useState(false)
  const [error, setError]           = useState('')

  async function handleSubmit(e) {
    e.preventDefault()

    if (!loginValue.trim()) { setError('// login não pode estar vazio'); return }
    if (!password.trim())   { setError('// senha não pode estar vazia'); return }

    setLoading(true)
    setError('')

    try {
      await doLogin(loginValue.trim(), password)
      navigate('/home', { replace: true })
    } catch (err) {
      const status = err?.response?.status
      if (status === 401)      setError('// credenciais inválidas')
      else if (status === 404) setError('// usuário não encontrado')
      else                     setError('// não foi possível conectar ao servidor')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="ln-page">

      {/* Aside decorativo */}
      <aside className="ln-aside">
        <div className="ln-aside-content">

          <div className="ln-lighthouse">
            <LighthouseIcon />
            <span className="ln-lighthouse-name">Pharos</span>
          </div>

          <div className="ln-aside-divider" />

          <h2 className="ln-aside-title">
            O conhecimento<br />
            <em>guia</em> quem<br />
            sabe buscá-lo.
          </h2>

          <p className="ln-aside-sub">
            Sistema de gestão<br />
            da Biblioteca Pharos<br />
            — acesso restrito
          </p>

          <div className="ln-aside-stat">
            <span className="ln-aside-stat-num">∞</span>
            <span className="ln-aside-stat-label">volumes catalogados</span>
          </div>

        </div>
      </aside>

      {/* Linha separadora */}
      <div className="ln-separator" aria-hidden="true" />

      {/* Formulário */}
      <main className="ln-main">
        <div className="ln-form-wrapper">

          <div className="ln-form-header">
            <span className="ln-form-tag">// autenticação</span>
            <h1 className="ln-form-title">Acesse o<br />acervo</h1>
            <p className="ln-form-sub">Entre com suas credenciais para continuar.</p>
          </div>

          <form onSubmit={handleSubmit} className="ln-form" noValidate>

            <div className="ln-field">
              <label htmlFor="ln-login">Login</label>
              <input
                id="ln-login"
                type="text"
                placeholder="seu.login"
                value={loginValue}
                onChange={(e) => { setLoginValue(e.target.value); setError('') }}
                autoComplete="username"
                autoFocus
              />
            </div>

            <div className="ln-field">
              <label htmlFor="ln-pass">Senha</label>
              <div className="ln-pass-wrapper">
                <input
                  id="ln-pass"
                  type={showPass ? 'text' : 'password'}
                  placeholder="••••••••"
                  value={password}
                  onChange={(e) => { setPassword(e.target.value); setError('') }}
                  autoComplete="current-password"
                />
                <button
                  type="button"
                  className="ln-pass-toggle"
                  onClick={() => setShowPass((s) => !s)}
                  aria-label={showPass ? 'Ocultar senha' : 'Mostrar senha'}
                >
                  {showPass ? (
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
                  )}
                </button>
              </div>
            </div>

            {error && (
              <div className="ln-error" role="alert">
                <svg width="12" height="12" viewBox="0 0 24 24" fill="none"
                  stroke="currentColor" strokeWidth="2">
                  <circle cx="12" cy="12" r="10"/>
                  <line x1="12" y1="8" x2="12" y2="12"/>
                  <line x1="12" y1="16" x2="12.01" y2="16"/>
                </svg>
                {error}
              </div>
            )}

            <button type="submit" className="ln-btn" disabled={loading}>
              {loading
                ? <span className="ln-btn-spinner" />
                : <span className="ln-btn-text">Entrar no sistema</span>
              }
            </button>

          </form>

          <div className="ln-footer">
            <span className="ln-footer-code">pharos.sys · {new Date().getFullYear()}</span>
          </div>

        </div>
      </main>

    </div>
  )
}
import { useState } from 'react'
import { useAuth } from '../../contexts/AuthContext'
import './Home.css'

/* ── Ícone de farol (reutilizado do Login) ── */
function LighthouseIcon({ size = 32 }) {
  return (
    <svg width={size} height={size} viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
      <rect x="20" y="6" width="8" height="6" rx="1" fill="#c8a96e" opacity="0.9"/>
      <path d="M18 12 L14 38 L34 38 L30 12 Z" fill="none" stroke="#c8a96e" strokeWidth="1.5" opacity="0.7"/>
      <rect x="14" y="38" width="20" height="3" rx="1" fill="#c8a96e" opacity="0.5"/>
      <line x1="14" y1="22" x2="34" y2="22" stroke="#c8a96e" strokeWidth="1" opacity="0.4"/>
      <line x1="13" y1="30" x2="35" y2="30" stroke="#c8a96e" strokeWidth="1" opacity="0.4"/>
      <line x1="24" y1="6" x2="24" y2="2"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.6"/>
      <line x1="24" y1="6" x2="30" y2="3"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.4"/>
      <line x1="24" y1="6" x2="18" y2="3"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.4"/>
      <line x1="24" y1="6" x2="34" y2="5"  stroke="#c8a96e" strokeWidth="1"   opacity="0.25"/>
      <line x1="24" y1="6" x2="14" y2="5"  stroke="#c8a96e" strokeWidth="1"   opacity="0.25"/>
    </svg>
  )
}

/* ── Ícones de nav ── */
function IconGrid() {
  return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/>
      <rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/>
    </svg>
  )
}
function IconBook() {
  return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"/><path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"/>
    </svg>
  )
}
function IconUsers() {
  return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/>
      <path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/>
    </svg>
  )
}
function IconSearch() {
  return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/>
    </svg>
  )
}
function IconSettings() {
  return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <circle cx="12" cy="12" r="3"/>
      <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1-2.83 2.83l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-4 0v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83-2.83l.06-.06A1.65 1.65 0 0 0 4.68 15a1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1 0-4h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 2.83-2.83l.06.06A1.65 1.65 0 0 0 9 4.68a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 4 0v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 2.83l-.06.06A1.65 1.65 0 0 0 19.4 9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 0 4h-.09a1.65 1.65 0 0 0-1.51 1z"/>
    </svg>
  )
}
function IconLogout() {
  return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
      <polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/>
    </svg>
  )
}
function IconArrow() {
  return (
    <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
      <line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/>
    </svg>
  )
}

/* ── dados de exemplo ── */
const STATS = [
  { label: 'volumes catalogados', value: '12.847', unit: null },
  { label: 'empréstimos ativos',  value: '342',    unit: null },
  { label: 'usuários cadastrados', value: '1.204', unit: null },
  { label: 'devoluções pendentes', value: '28',    unit: null, alert: true },
]

const RECENT_LOANS = [
  { id: '#8821', title: 'O Nome da Rosa',            author: 'Umberto Eco',      user: 'm.santos',  due: '22 mai', status: 'ativo' },
  { id: '#8820', title: 'Cem Anos de Solidão',       author: 'García Márquez',   user: 'r.lima',    due: '20 mai', status: 'atrasado' },
  { id: '#8819', title: 'A Montanha Mágica',         author: 'Thomas Mann',      user: 'p.costa',   due: '25 mai', status: 'ativo' },
  { id: '#8818', title: 'Ficções',                   author: 'Jorge L. Borges',  user: 'a.rocha',   due: '18 mai', status: 'ativo' },
  { id: '#8817', title: 'O Processo',                author: 'Franz Kafka',      user: 'c.mendes',  due: '15 mai', status: 'atrasado' },
]

const QUICK_ACTIONS = [
  { label: 'Novo empréstimo',    tag: '// loan.new',    icon: <IconBook /> },
  { label: 'Cadastrar volume',   tag: '// book.create', icon: <IconBook /> },
  { label: 'Buscar no acervo',   tag: '// search.run',  icon: <IconSearch /> },
  { label: 'Registrar usuário',  tag: '// user.create', icon: <IconUsers /> },
]

const NAV_ITEMS = [
  { label: 'Painel',    icon: <IconGrid />,     active: true },
  { label: 'Acervo',   icon: <IconBook />,     active: false },
  { label: 'Usuários', icon: <IconUsers />,    active: false },
  { label: 'Busca',    icon: <IconSearch />,   active: false },
  { label: 'Config.',  icon: <IconSettings />, active: false },
]

export default function Home() {
  const { user, logout } = useAuth()
  const [navOpen, setNavOpen] = useState(false)

  const displayName = user?.nome ?? user?.name ?? user?.login ?? 'Bibliotecário'
  const initials    = displayName.slice(0, 2).toUpperCase()

  return (
    <div className="hm-layout">

      {/* ── Sidebar ── */}
      <nav className={`hm-sidebar ${navOpen ? 'hm-sidebar--open' : ''}`}>

        <div className="hm-sidebar-brand">
          <LighthouseIcon size={28} />
          <span className="hm-brand-name">Pharos</span>
        </div>

        <div className="hm-sidebar-divider" />

        <ul className="hm-nav-list">
          {NAV_ITEMS.map((item) => (
            <li key={item.label}>
              <button className={`hm-nav-item ${item.active ? 'hm-nav-item--active' : ''}`}>
                <span className="hm-nav-icon">{item.icon}</span>
                <span className="hm-nav-label">{item.label}</span>
                {item.active && <span className="hm-nav-pip" />}
              </button>
            </li>
          ))}
        </ul>

        <div className="hm-sidebar-footer">
          <div className="hm-user-chip">
            <span className="hm-user-avatar">{initials}</span>
            <div className="hm-user-info">
              <span className="hm-user-name">{displayName}</span>
              <span className="hm-user-role">// acesso autorizado</span>
            </div>
          </div>

          <button className="hm-logout-btn" onClick={logout} title="Sair">
            <IconLogout />
            <span>Sair</span>
          </button>
        </div>
      </nav>

      {/* overlay mobile */}
      {navOpen && <div className="hm-overlay" onClick={() => setNavOpen(false)} />}

      {/* ── Main ── */}
      <main className="hm-main">

        {/* topbar mobile */}
        <div className="hm-topbar">
          <button className="hm-menu-btn" onClick={() => setNavOpen((v) => !v)} aria-label="Menu">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round">
              <line x1="3" y1="6"  x2="21" y2="6"/>
              <line x1="3" y1="12" x2="21" y2="12"/>
              <line x1="3" y1="18" x2="21" y2="18"/>
            </svg>
          </button>
          <div className="hm-topbar-brand">
            <LighthouseIcon size={22} />
            <span>Pharos</span>
          </div>
        </div>

        {/* ── conteúdo ── */}
        <div className="hm-content">

          {/* cabeçalho */}
          <header className="hm-header">
            <div>
              <span className="hm-header-tag">// painel · biblioteca</span>
              <h1 className="hm-header-title">
                Bom dia,<br />
                <em>{displayName.split(' ')[0]}</em>.
              </h1>
              <p className="hm-header-sub">
                {new Date().toLocaleDateString('pt-BR', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' })}
              </p>
            </div>
          </header>

          {/* ── grid de estatísticas ── */}
          <section className="hm-section">
            <span className="hm-section-tag">// visão geral</span>
            <div className="hm-stats-grid">
              {STATS.map((s) => (
                <div key={s.label} className={`hm-stat-card ${s.alert ? 'hm-stat-card--alert' : ''}`}>
                  <span className="hm-stat-value">{s.value}</span>
                  <span className="hm-stat-label">{s.label}</span>
                  {s.alert && <span className="hm-stat-alert-pip" />}
                </div>
              ))}
            </div>
          </section>

          {/* ── grid inferior: empréstimos + ações ── */}
          <div className="hm-bottom-grid">

            {/* tabela de empréstimos recentes */}
            <section className="hm-section hm-section--loans">
              <div className="hm-section-header">
                <span className="hm-section-tag">// empréstimos recentes</span>
                <button className="hm-link-btn">ver todos <IconArrow /></button>
              </div>

              <div className="hm-table-wrap">
                <table className="hm-table">
                  <thead>
                    <tr>
                      <th>id</th>
                      <th>título</th>
                      <th>usuário</th>
                      <th>devolução</th>
                      <th>status</th>
                    </tr>
                  </thead>
                  <tbody>
                    {RECENT_LOANS.map((loan) => (
                      <tr key={loan.id} className="hm-table-row">
                        <td className="hm-td-id">{loan.id}</td>
                        <td>
                          <span className="hm-td-title">{loan.title}</span>
                          <span className="hm-td-author">{loan.author}</span>
                        </td>
                        <td className="hm-td-user">{loan.user}</td>
                        <td className="hm-td-due">{loan.due}</td>
                        <td>
                          <span className={`hm-badge hm-badge--${loan.status}`}>
                            {loan.status}
                          </span>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </section>

            {/* ações rápidas */}
            <section className="hm-section hm-section--actions">
              <span className="hm-section-tag">// ações rápidas</span>
              <div className="hm-actions-list">
                {QUICK_ACTIONS.map((action) => (
                  <button key={action.label} className="hm-action-card">
                    <span className="hm-action-icon">{action.icon}</span>
                    <div className="hm-action-text">
                      <span className="hm-action-tag">{action.tag}</span>
                      <span className="hm-action-label">{action.label}</span>
                    </div>
                    <span className="hm-action-arrow"><IconArrow /></span>
                  </button>
                ))}
              </div>
            </section>

          </div>
        </div>

        {/* rodapé */}
        <footer className="hm-footer">
          <span>pharos.sys · {new Date().getFullYear()}</span>
          <span>v0.1.0-alpha</span>
        </footer>

      </main>
    </div>
  )
}
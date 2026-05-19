import { useNavigate } from 'react-router-dom'
import './Landing.css'

const FEATURES = [
  {
    code: '01',
    title: 'Acervo digital',
    desc: 'Cadastre, edite e organize livros por título, autor, categoria e ISBN. Controle de cópias disponíveis em tempo real.',
  },
  {
    code: '02',
    title: 'Gestão de membros',
    desc: 'Cadastro completo de membros da biblioteca com histórico de empréstimos e status de atividade.',
  },
  {
    code: '03',
    title: 'Empréstimos e devoluções',
    desc: 'Fluxo completo de empréstimo com prazos, renovações e controle de status — ativo, devolvido ou vencido.',
  },
  {
    code: '04',
    title: 'Dashboard analítico',
    desc: 'Métricas em tempo real: volumes em circulação, empréstimos do mês, membros ativos e livros mais requisitados.',
  },
  {
    code: '05',
    title: 'Controle de acesso',
    desc: 'Dois níveis de permissão — Administrador e Bibliotecário — com autenticação JWT segura.',
  },
  {
    code: '06',
    title: 'API RESTful',
    desc: 'Backend em Spring Boot 4 com PostgreSQL, Flyway e cobertura de testes de integração.',
  },
]

export default function Landing() {
  const navigate = useNavigate()

  return (
    <div className="ld-page">

      {/* ── Navbar ── */}
      <nav className="ld-nav">
        <div className="ld-nav-brand">
          <LighthouseIcon />
          <span className="ld-nav-name">Pharos</span>
        </div>
        <div className="ld-nav-actions">
          <button className="ld-nav-link" onClick={() => navigate('/cadastro')}>
            Criar conta
          </button>
          <button className="ld-nav-btn" onClick={() => navigate('/login')}>
            Acessar sistema
          </button>
        </div>
      </nav>

      {/* ── Hero ── */}
      <section className="ld-hero">
        <div className="ld-hero-inner">
          <span className="ld-tag">// sistema de gestão de bibliotecas</span>
          <h1 className="ld-hero-title">
            O farol que guia<br />
            <em>o conhecimento.</em>
          </h1>
          <p className="ld-hero-sub">
            Pharos é um sistema moderno para gestão de acervos, membros e empréstimos.
            Construído para bibliotecas que levam o conhecimento a sério.
          </p>
          <div className="ld-hero-actions">
            <button className="ld-btn-primary" onClick={() => navigate('/login')}>
              Entrar no sistema
            </button>
            <button className="ld-btn-secondary" onClick={() => navigate('/cadastro')}>
              Criar conta
            </button>
            <a className="ld-btn-ghost" href="#features">
              Ver funcionalidades
            </a>
          </div>
        </div>

        {/* Ornamento visual */}
        <div className="ld-hero-orb" aria-hidden="true" />
        <div className="ld-hero-ring" aria-hidden="true" />
      </section>

      {/* ── Divider ── */}
      <div className="ld-divider">
        <span className="ld-divider-line" />
        <span className="ld-divider-glyph">⬡</span>
        <span className="ld-divider-line" />
      </div>

      {/* ── Features ── */}
      <section className="ld-features" id="features">
        <div className="ld-section-header">
          <span className="ld-tag">// funcionalidades</span>
          <h2 className="ld-section-title">O que o Pharos oferece</h2>
        </div>

        <div className="ld-features-grid">
          {FEATURES.map((f) => (
            <div className="ld-card" key={f.code}>
              <span className="ld-card-code">{f.code}</span>
              <h3 className="ld-card-title">{f.title}</h3>
              <p className="ld-card-desc">{f.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* ── Divider ── */}
      <div className="ld-divider">
        <span className="ld-divider-line" />
        <span className="ld-divider-glyph">⬡</span>
        <span className="ld-divider-line" />
      </div>

      {/* ── Stack / Sobre ── */}
      <section className="ld-about">
        <div className="ld-section-header">
          <span className="ld-tag">// sobre o projeto</span>
          <h2 className="ld-section-title">Tecnologia por baixo</h2>
        </div>

        <div className="ld-stack">
          <div className="ld-stack-col">
            <span className="ld-stack-label">Backend</span>
            <ul className="ld-stack-list">
              <li>Java 17 + Spring Boot 4</li>
              <li>PostgreSQL + Flyway</li>
              <li>JWT Auth (auth0)</li>
              <li>Testes de integração</li>
            </ul>
          </div>
          <div className="ld-stack-divider" />
          <div className="ld-stack-col">
            <span className="ld-stack-label">Frontend</span>
            <ul className="ld-stack-list">
              <li>React 18 + Vite</li>
              <li>React Router DOM</li>
              <li>Axios</li>
              <li>CSS modular</li>
            </ul>
          </div>
          <div className="ld-stack-divider" />
          <div className="ld-stack-col">
            <span className="ld-stack-label">Conceito</span>
            <ul className="ld-stack-list">
              <li>Inspirado na Biblioteca</li>
              <li>de Alexandria e no</li>
              <li>Farol de Pharos —</li>
              <li>guia e iluminação.</li>
            </ul>
          </div>
        </div>

        <div className="ld-cta">
          <p className="ld-cta-text">Pronto para explorar o acervo?</p>
          <button className="ld-btn-primary" onClick={() => navigate('/login')}>
            Acessar o Pharos
          </button>
        </div>
      </section>

      {/* ── Rodapé ── */}
      <footer className="ld-footer">
        <div className="ld-footer-brand">
          <LighthouseIcon small />
          <span>Pharos</span>
        </div>
        <span className="ld-footer-copy">
          Projeto de portfólio · {new Date().getFullYear()}
        </span>
      </footer>

    </div>
  )
}

function LighthouseIcon({ small }) {
  const s = small ? 24 : 32
  return (
    <svg width={s} height={s} viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
      <rect x="20" y="6" width="8" height="6" rx="1" fill="#c8a96e" opacity="0.9"/>
      <path d="M18 12 L14 38 L34 38 L30 12 Z" fill="none" stroke="#c8a96e" strokeWidth="1.5" opacity="0.7"/>
      <rect x="14" y="38" width="20" height="3" rx="1" fill="#c8a96e" opacity="0.5"/>
      <line x1="14" y1="22" x2="34" y2="22" stroke="#c8a96e" strokeWidth="1" opacity="0.4"/>
      <line x1="13" y1="30" x2="35" y2="30" stroke="#c8a96e" strokeWidth="1" opacity="0.4"/>
      <line x1="24" y1="6" x2="24" y2="2"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.6"/>
      <line x1="24" y1="6" x2="30" y2="3"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.4"/>
      <line x1="24" y1="6" x2="18" y2="3"  stroke="#c8a96e" strokeWidth="1.5" opacity="0.4"/>
    </svg>
  )
}
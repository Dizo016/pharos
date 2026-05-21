-- ═══════════════════════════════════════════════════════════════
-- Pharos — Seed de dados iniciais
-- V5__seed_initial_data.sql
--
-- Conteúdo:
--   • 2 usuários  (1 ADMIN + 1 LIBRARIAN)
--   • 30 membros
--   • 40 livros   (categorias variadas, ISBNs reais)
--   • 25 empréstimos (ACTIVE, RETURNED, OVERDUE)
-- ──────────────────────────────────────────
-- MEMBROS
-- ──────────────────────────────────────────
INSERT INTO members (id, name, email, phone, notes, active) VALUES
('b1000000-0000-0000-0000-000000000001', 'Carlos Eduardo Mendes',   'carlos.mendes@email.com',   '(11) 98765-4321', null,                          true),
('b1000000-0000-0000-0000-000000000002', 'Mariana Souza Lima',      'mariana.lima@email.com',    '(21) 99123-4567', null,                          true),
('b1000000-0000-0000-0000-000000000003', 'Pedro Henrique Costa',    'pedro.costa@email.com',     '(31) 97654-3210', 'Prefere ficção científica',   true),
('b1000000-0000-0000-0000-000000000004', 'Juliana Ferreira Rocha',  'juliana.rocha@email.com',   '(41) 98888-1234', null,                          true),
('b1000000-0000-0000-0000-000000000005', 'Rafael Oliveira Santos',  'rafael.santos@email.com',   '(51) 96543-2109', null,                          true),
('b1000000-0000-0000-0000-000000000006', 'Fernanda Alves Pinto',    'fernanda.pinto@email.com',  '(61) 95432-1098', 'Pesquisadora — história',     true),
('b1000000-0000-0000-0000-000000000007', 'Thiago Nascimento Cruz',  'thiago.cruz@email.com',     '(71) 94321-0987', null,                          true),
('b1000000-0000-0000-0000-000000000008', 'Beatriz Cardoso Melo',    'beatriz.melo@email.com',    '(81) 93210-9876', null,                          true),
('b1000000-0000-0000-0000-000000000009', 'Leonardo Teixeira Nunes', 'leonardo.nunes@email.com',  '(91) 92109-8765', null,                          true),
('b1000000-0000-0000-0000-000000000010', 'Amanda Ribeiro Farias',   'amanda.farias@email.com',   '(11) 91098-7654', 'Aluna de letras',             true),
('b1000000-0000-0000-0000-000000000011', 'Gustavo Pereira Moura',   'gustavo.moura@email.com',   '(21) 90987-6543', null,                          true),
('b1000000-0000-0000-0000-000000000012', 'Camila Gomes Barbosa',    'camila.barbosa@email.com',  '(31) 99876-5432', null,                          true),
('b1000000-0000-0000-0000-000000000013', 'Diego Martins Azevedo',   'diego.azevedo@email.com',   '(41) 98765-4321', null,                          true),
('b1000000-0000-0000-0000-000000000014', 'Letícia Campos Vieira',   'leticia.vieira@email.com',  '(51) 97654-3210', 'Professora universitária',    true),
('b1000000-0000-0000-0000-000000000015', 'Bruno Araújo Lopes',      'bruno.lopes@email.com',     '(61) 96543-2109', null,                          true),
('b1000000-0000-0000-0000-000000000016', 'Isabela Freitas Duarte',  'isabela.duarte@email.com',  '(71) 95432-1098', null,                          true),
('b1000000-0000-0000-0000-000000000017', 'Matheus Carvalho Dias',   'matheus.dias@email.com',    '(81) 94321-0987', null,                          true),
('b1000000-0000-0000-0000-000000000018', 'Priscila Monteiro Serra', 'priscila.serra@email.com',  '(91) 93210-9876', null,                          true),
('b1000000-0000-0000-0000-000000000019', 'Felipe Cunha Ramos',      'felipe.ramos@email.com',    '(11) 92109-8765', null,                          true),
('b1000000-0000-0000-0000-000000000020', 'Vanessa Torres Bastos',   'vanessa.bastos@email.com',  '(21) 91098-7654', 'Doutoranda em filosofia',     true),
('b1000000-0000-0000-0000-000000000021', 'Rodrigo Fonseca Lima',    'rodrigo.lima@email.com',    '(31) 90987-6543', null,                          true),
('b1000000-0000-0000-0000-000000000022', 'Natália Correia Braga',   'natalia.braga@email.com',   '(41) 99876-5432', null,                          true),
('b1000000-0000-0000-0000-000000000023', 'André Machado Viana',     'andre.viana@email.com',     '(51) 98765-4321', null,                          true),
('b1000000-0000-0000-0000-000000000024', 'Patrícia Nogueira Leite', 'patricia.leite@email.com',  '(61) 97654-3210', null,                          true),
('b1000000-0000-0000-0000-000000000025', 'Henrique Silveira Paiva', 'henrique.paiva@email.com',  '(71) 96543-2109', null,                          true),
('b1000000-0000-0000-0000-000000000026', 'Larissa Medeiros Castro', 'larissa.castro@email.com',  '(81) 95432-1098', null,                          true),
('b1000000-0000-0000-0000-000000000027', 'Vinicius Borges Matos',   'vinicius.matos@email.com',  '(91) 94321-0987', null,                          true),
('b1000000-0000-0000-0000-000000000028', 'Renata Pinheiro Sousa',   'renata.sousa@email.com',    '(11) 93210-9876', null,                          true),
('b1000000-0000-0000-0000-000000000029', 'Marcos Tavares Neto',     'marcos.neto@email.com',     '(21) 92109-8765', null,                          true),
('b1000000-0000-0000-0000-000000000030', 'Simone Quaresma Reis',    'simone.reis@email.com',     '(31) 91098-7654', 'Membro fundador da biblioteca', true);

-- ──────────────────────────────────────────
-- LIVROS
-- ──────────────────────────────────────────
INSERT INTO books (id, title, author, isbn, publisher, published_year, category, total_copies, available_copies, description, active) VALUES

-- FICTION
('c1000000-0000-0000-0000-000000000001', 'O Nome da Rosa',                 'Umberto Eco',              '9788572326643', 'Record',         1980, 'FICTION',       3, 2, 'Um monge investiga mortes misteriosas em um mosteiro medieval.',        true),
('c1000000-0000-0000-0000-000000000002', 'Cem Anos de Solidão',            'Gabriel García Márquez',   '9788535909494', 'Record',         1967, 'FICTION',       4, 2, 'A saga da família Buendía na cidade fictícia de Macondo.',              true),
('c1000000-0000-0000-0000-000000000003', 'A Montanha Mágica',              'Thomas Mann',              '9788535921472', 'Nova Fronteira', 1924, 'FICTION',       2, 2, 'Hans Castorp passa sete anos num sanatório nos Alpes suíços.',          true),
('c1000000-0000-0000-0000-000000000004', 'O Processo',                     'Franz Kafka',              '9788525052247', 'Companhia',      1925, 'FICTION',       3, 1, 'Josef K. é preso sem saber o motivo e tenta se defender.',              true),
('c1000000-0000-0000-0000-000000000005', 'Ficções',                        'Jorge Luis Borges',        '9788535909661', 'Companhia',      1944, 'FICTION',       3, 3, 'Coletânea de contos que exploram labirintos, espelhos e infinitos.',    true),
('c1000000-0000-0000-0000-000000000006', 'Madame Bovary',                  'Gustave Flaubert',         '9788535908091', 'Penguin',        1857, 'FICTION',       2, 2, 'Emma Bovary busca escapar do tédio da vida provinciana.',              true),
('c1000000-0000-0000-0000-000000000007', 'Crime e Castigo',                'Fiódor Dostoiévski',       '9788535914481', 'Record',         1866, 'FICTION',       3, 2, 'Raskólnikov mata uma agiota e enfrenta o peso da culpa.',               true),
('c1000000-0000-0000-0000-000000000008', 'O Mestre e Margarida',           'Mikhail Bulgákov',         '9788535911251', 'Record',         1967, 'FICTION',       2, 2, 'O Diabo visita Moscou soviética com consequências caóticas.',           true),

-- SCIENCE_FICTION
('c1000000-0000-0000-0000-000000000009', 'Fundação',                       'Isaac Asimov',             '9788576572008', 'Aleph',          1951, 'SCIENCE_FICTION', 4, 2, 'Hari Seldon cria a Fundação para preservar o conhecimento humano.',    true),
('c1000000-0000-0000-0000-000000000010', 'Duna',                           'Frank Herbert',            '9788576571988', 'Aleph',          1965, 'SCIENCE_FICTION', 3, 1, 'Paul Atreides luta pelo controle do planeta desértico Arrakis.',        true),
('c1000000-0000-0000-0000-000000000011', 'Neuromancer',                    'William Gibson',           '9788576572121', 'Aleph',          1984, 'SCIENCE_FICTION', 2, 2, 'Um hacker é contratado para uma missão no ciberespaço.',               true),
('c1000000-0000-0000-0000-000000000012', 'O Guia do Mochileiro das Galáxias', 'Douglas Adams',         '9788599296820', 'Sextante',       1979, 'SCIENCE_FICTION', 3, 3, 'Arthur Dent sobrevive à destruição da Terra e viaja pelo universo.',   true),

-- PHILOSOPHY
('c1000000-0000-0000-0000-000000000013', 'A República',                    'Platão',                   '9788572836012', 'Nova Fronteira', -380, 'PHILOSOPHY',    2, 2, 'Diálogos sobre justiça, política e o estado ideal.',                   true),
('c1000000-0000-0000-0000-000000000014', 'Assim Falou Zaratustra',         'Friedrich Nietzsche',      '9788525049551', 'Vozes',          1883, 'PHILOSOPHY',    3, 2, 'Zaratustra desce da montanha para proclamar a morte de Deus.',          true),
('c1000000-0000-0000-0000-000000000015', 'Meditações',                     'Marco Aurélio',            '9788582850152', 'Edipro',         180,  'PHILOSOPHY',    2, 2, 'Reflexões estoicas do imperador romano sobre virtude e razão.',         true),
('c1000000-0000-0000-0000-000000000016', 'O Ser e o Nada',                 'Jean-Paul Sartre',         '9788532648433', 'Vozes',          1943, 'PHILOSOPHY',    2, 1, 'Tratado sobre o existencialismo e a liberdade humana.',                 true),

-- HISTORY
('c1000000-0000-0000-0000-000000000017', 'Sapiens',                        'Yuval Noah Harari',        '9788535919684', 'Companhia',      2011, 'HISTORY',       5, 3, 'Uma breve história da humanidade desde a pré-história até hoje.',       true),
('c1000000-0000-0000-0000-000000000018', 'O Declínio e Queda do Império Romano', 'Edward Gibbon',      '9788577991624', 'Folha de SP',    1776, 'HISTORY',       2, 2, 'A monumental história do colapso de Roma.',                            true),
('c1000000-0000-0000-0000-000000000019', 'Homo Deus',                      'Yuval Noah Harari',        '9788535927962', 'Companhia',      2015, 'HISTORY',       3, 2, 'Uma breve história do amanhã e o futuro da humanidade.',               true),
('c1000000-0000-0000-0000-000000000020', '1808',                           'Laurentino Gomes',         '9788580576511', 'Globo Livros',   2007, 'HISTORY',       3, 3, 'A chegada da família real portuguesa ao Brasil.',                       true),

-- TECHNOLOGY
('c1000000-0000-0000-0000-000000000021', 'Código Limpo',                   'Robert C. Martin',         '9788576082675', 'Alta Books',     2008, 'TECHNOLOGY',    3, 1, 'Práticas para escrever código legível e sustentável.',                  true),
('c1000000-0000-0000-0000-000000000022', 'O Mítico Homem-Mês',             'Frederick P. Brooks Jr.',  '9788550800776', 'Alta Books',     1975, 'TECHNOLOGY',    2, 2, 'Ensaios sobre engenharia de software e gerenciamento de projetos.',     true),
('c1000000-0000-0000-0000-000000000023', 'Design Patterns',                'Gang of Four',             '9780201633610', 'Addison-Wesley', 1994, 'TECHNOLOGY',    2, 2, 'Padrões de projeto reutilizáveis em orientação a objetos.',             true),
('c1000000-0000-0000-0000-000000000024', 'The Pragmatic Programmer',       'David Thomas / A. Hunt',   '9780135957059', 'Addison-Wesley', 1999, 'TECHNOLOGY',    2, 2, 'Dicas práticas para se tornar um programador melhor.',                  true),

-- BIOGRAPHY
('c1000000-0000-0000-0000-000000000025', 'Steve Jobs',                     'Walter Isaacson',          '9788535919387', 'Companhia',      2011, 'BIOGRAPHY',     3, 2, 'A biografia autorizada do cofundador da Apple.',                        true),
('c1000000-0000-0000-0000-000000000026', 'Leonardo da Vinci',              'Walter Isaacson',          '9788551002391', 'Intrínseca',     2017, 'BIOGRAPHY',     2, 2, 'A vida e a mente do gênio da Renascença.',                             true),
('c1000000-0000-0000-0000-000000000027', 'Diário de Anne Frank',           'Anne Frank',               '9788501046406', 'Record',         1947, 'BIOGRAPHY',     4, 3, 'O diário da adolescente judia durante a Segunda Guerra Mundial.',       true),

-- LITERATURE
('c1000000-0000-0000-0000-000000000028', 'Dom Casmurro',                   'Machado de Assis',         '9788572328821', 'Martin Claret',  1899, 'LITERATURE',    4, 3, 'Bentinho narra sua vida e a suspeita de traição de Capitu.',            true),
('c1000000-0000-0000-0000-000000000029', 'Grande Sertão: Veredas',         'João Guimarães Rosa',      '9788535909753', 'Nova Fronteira', 1956, 'LITERATURE',    3, 2, 'Riobaldo narra sua vida de jagunço no sertão mineiro.',                 true),
('c1000000-0000-0000-0000-000000000030', 'Memórias Póstumas de Brás Cubas','Machado de Assis',         '9788572327107', 'Martin Claret',  1881, 'LITERATURE',    3, 3, 'Um defunto-autor narra sua própria vida de além-túmulo.',              true),
('c1000000-0000-0000-0000-000000000031', 'A Hora da Estrela',              'Clarice Lispector',        '9788532630216', 'Rocco',          1977, 'LITERATURE',    3, 2, 'A história de Macabéa, uma nordestina perdida no Rio de Janeiro.',      true),

-- MYSTERY
('c1000000-0000-0000-0000-000000000032', 'O Cão dos Baskervilles',         'Arthur Conan Doyle',       '9788578272357', 'L&PM',           1902, 'MYSTERY',       3, 2, 'Sherlock Holmes investiga a lenda de um cão demoníaco.',                true),
('c1000000-0000-0000-0000-000000000033', 'E Não Sobrou Nenhum',            'Agatha Christie',          '9788595081215', 'HarperCollins',  1939, 'MYSTERY',       3, 3, 'Dez pessoas são convidadas para uma ilha e começam a morrer.',          true),
('c1000000-0000-0000-0000-000000000034', 'O Código Da Vinci',              'Dan Brown',                '9788575421498', 'Sextante',       2003, 'MYSTERY',       4, 2, 'Robert Langdon investiga um assassinato no Louvre.',                    true),

-- ECONOMICS
('c1000000-0000-0000-0000-000000000035', 'A Riqueza das Nações',           'Adam Smith',               '9788562022159', 'Madras',         1776, 'ECONOMICS',     2, 2, 'O texto fundador da economia política moderna.',                       true),
('c1000000-0000-0000-0000-000000000036', 'O Capital',                      'Karl Marx',                '9788579601187', 'Boitempo',       1867, 'ECONOMICS',     2, 1, 'Análise crítica do modo de produção capitalista.',                      true),
('c1000000-0000-0000-0000-000000000037', 'Freakonomics',                   'Steven D. Levitt',         '9788535907681', 'Companhia',      2005, 'ECONOMICS',     3, 3, 'Economistas exploram o lado oculto de tudo.',                          true),

-- POETRY
('c1000000-0000-0000-0000-000000000038', 'Poesia Completa — Fernando Pessoa', 'Fernando Pessoa',       '9789720115058', 'Porto Editora',  2014, 'POETRY',        2, 2, 'Obra poética completa de Fernando Pessoa e seus heterônimos.',          true),
('c1000000-0000-0000-0000-000000000039', 'Drummond — A Rosa do Povo',      'Carlos Drummond de Andrade','9788520923513','Record',         1945, 'POETRY',        2, 2, 'Um dos livros mais importantes da poesia brasileira.',                  true),
('c1000000-0000-0000-0000-000000000040', 'Flores do Mal',                  'Charles Baudelaire',       '9788535905557', 'Nova Fronteira', 1857, 'POETRY',        2, 2, 'Coletânea que marcou a transição para o simbolismo francês.',           true);

-- ──────────────────────────────────────────
-- EMPRÉSTIMOS
-- Status: ACTIVE, RETURNED, OVERDUE
-- ──────────────────────────────────────────
INSERT INTO loans (id, member_id, book_id, loan_date, due_date, return_date, status, notes, created_at) VALUES

-- ACTIVE (dentro do prazo)
('d1000000-0000-0000-0000-000000000001', 'b1000000-0000-0000-0000-000000000001', 'c1000000-0000-0000-0000-000000000001', CURRENT_DATE - 5,  CURRENT_DATE + 9,  null, 'ACTIVE',    null, NOW() - INTERVAL '5 days'),
('d1000000-0000-0000-0000-000000000002', 'b1000000-0000-0000-0000-000000000002', 'c1000000-0000-0000-0000-000000000002', CURRENT_DATE - 3,  CURRENT_DATE + 11, null, 'ACTIVE',    null, NOW() - INTERVAL '3 days'),
('d1000000-0000-0000-0000-000000000003', 'b1000000-0000-0000-0000-000000000003', 'c1000000-0000-0000-0000-000000000009', CURRENT_DATE - 7,  CURRENT_DATE + 7,  null, 'ACTIVE',    null, NOW() - INTERVAL '7 days'),
('d1000000-0000-0000-0000-000000000004', 'b1000000-0000-0000-0000-000000000004', 'c1000000-0000-0000-0000-000000000010', CURRENT_DATE - 2,  CURRENT_DATE + 12, null, 'ACTIVE',    null, NOW() - INTERVAL '2 days'),
('d1000000-0000-0000-0000-000000000005', 'b1000000-0000-0000-0000-000000000005', 'c1000000-0000-0000-0000-000000000017', CURRENT_DATE - 1,  CURRENT_DATE + 13, null, 'ACTIVE',    null, NOW() - INTERVAL '1 day'),
('d1000000-0000-0000-0000-000000000006', 'b1000000-0000-0000-0000-000000000006', 'c1000000-0000-0000-0000-000000000021', CURRENT_DATE - 4,  CURRENT_DATE + 10, null, 'ACTIVE',    'Para pesquisa acadêmica', NOW() - INTERVAL '4 days'),
('d1000000-0000-0000-0000-000000000007', 'b1000000-0000-0000-0000-000000000007', 'c1000000-0000-0000-0000-000000000025', CURRENT_DATE - 6,  CURRENT_DATE + 8,  null, 'ACTIVE',    null, NOW() - INTERVAL '6 days'),
('d1000000-0000-0000-0000-000000000008', 'b1000000-0000-0000-0000-000000000008', 'c1000000-0000-0000-0000-000000000028', CURRENT_DATE - 8,  CURRENT_DATE + 6,  null, 'ACTIVE',    null, NOW() - INTERVAL '8 days'),
('d1000000-0000-0000-0000-000000000009', 'b1000000-0000-0000-0000-000000000010', 'c1000000-0000-0000-0000-000000000032', CURRENT_DATE - 3,  CURRENT_DATE + 11, null, 'ACTIVE',    null, NOW() - INTERVAL '3 days'),
('d1000000-0000-0000-0000-000000000010', 'b1000000-0000-0000-0000-000000000012', 'c1000000-0000-0000-0000-000000000004', CURRENT_DATE - 1,  CURRENT_DATE + 13, null, 'ACTIVE',    null, NOW() - INTERVAL '1 day'),

-- OVERDUE (prazo vencido, não devolvido)
('d1000000-0000-0000-0000-000000000011', 'b1000000-0000-0000-0000-000000000009', 'c1000000-0000-0000-0000-000000000007', CURRENT_DATE - 20, CURRENT_DATE - 6,  null, 'OVERDUE',   null, NOW() - INTERVAL '20 days'),
('d1000000-0000-0000-0000-000000000012', 'b1000000-0000-0000-0000-000000000011', 'c1000000-0000-0000-0000-000000000016', CURRENT_DATE - 25, CURRENT_DATE - 11, null, 'OVERDUE',   null, NOW() - INTERVAL '25 days'),
('d1000000-0000-0000-0000-000000000013', 'b1000000-0000-0000-0000-000000000014', 'c1000000-0000-0000-0000-000000000036', CURRENT_DATE - 30, CURRENT_DATE - 16, null, 'OVERDUE',   null, NOW() - INTERVAL '30 days'),
('d1000000-0000-0000-0000-000000000014', 'b1000000-0000-0000-0000-000000000020', 'c1000000-0000-0000-0000-000000000014', CURRENT_DATE - 18, CURRENT_DATE - 4,  null, 'OVERDUE',   null, NOW() - INTERVAL '18 days'),
('d1000000-0000-0000-0000-000000000015', 'b1000000-0000-0000-0000-000000000023', 'c1000000-0000-0000-0000-000000000029', CURRENT_DATE - 22, CURRENT_DATE - 8,  null, 'OVERDUE',   null, NOW() - INTERVAL '22 days'),

-- RETURNED (devolvidos)
('d1000000-0000-0000-0000-000000000016', 'b1000000-0000-0000-0000-000000000013', 'c1000000-0000-0000-0000-000000000005', CURRENT_DATE - 30, CURRENT_DATE - 16, CURRENT_DATE - 18, 'RETURNED', null, NOW() - INTERVAL '30 days'),
('d1000000-0000-0000-0000-000000000017', 'b1000000-0000-0000-0000-000000000015', 'c1000000-0000-0000-0000-000000000012', CURRENT_DATE - 28, CURRENT_DATE - 14, CURRENT_DATE - 15, 'RETURNED', null, NOW() - INTERVAL '28 days'),
('d1000000-0000-0000-0000-000000000018', 'b1000000-0000-0000-0000-000000000016', 'c1000000-0000-0000-0000-000000000013', CURRENT_DATE - 40, CURRENT_DATE - 26, CURRENT_DATE - 27, 'RETURNED', null, NOW() - INTERVAL '40 days'),
('d1000000-0000-0000-0000-000000000019', 'b1000000-0000-0000-0000-000000000017', 'c1000000-0000-0000-0000-000000000022', CURRENT_DATE - 35, CURRENT_DATE - 21, CURRENT_DATE - 22, 'RETURNED', null, NOW() - INTERVAL '35 days'),
('d1000000-0000-0000-0000-000000000020', 'b1000000-0000-0000-0000-000000000018', 'c1000000-0000-0000-0000-000000000033', CURRENT_DATE - 45, CURRENT_DATE - 31, CURRENT_DATE - 30, 'RETURNED', null, NOW() - INTERVAL '45 days'),
('d1000000-0000-0000-0000-000000000021', 'b1000000-0000-0000-0000-000000000019', 'c1000000-0000-0000-0000-000000000038', CURRENT_DATE - 50, CURRENT_DATE - 36, CURRENT_DATE - 37, 'RETURNED', null, NOW() - INTERVAL '50 days'),
('d1000000-0000-0000-0000-000000000022', 'b1000000-0000-0000-0000-000000000021', 'c1000000-0000-0000-0000-000000000017', CURRENT_DATE - 60, CURRENT_DATE - 46, CURRENT_DATE - 48, 'RETURNED', null, NOW() - INTERVAL '60 days'),
('d1000000-0000-0000-0000-000000000023', 'b1000000-0000-0000-0000-000000000024', 'c1000000-0000-0000-0000-000000000027', CURRENT_DATE - 55, CURRENT_DATE - 41, CURRENT_DATE - 42, 'RETURNED', null, NOW() - INTERVAL '55 days'),
('d1000000-0000-0000-0000-000000000024', 'b1000000-0000-0000-0000-000000000026', 'c1000000-0000-0000-0000-000000000034', CURRENT_DATE - 42, CURRENT_DATE - 28, CURRENT_DATE - 29, 'RETURNED', null, NOW() - INTERVAL '42 days'),
('d1000000-0000-0000-0000-000000000025', 'b1000000-0000-0000-0000-000000000030', 'c1000000-0000-0000-0000-000000000037', CURRENT_DATE - 38, CURRENT_DATE - 24, CURRENT_DATE - 25, 'RETURNED', null, NOW() - INTERVAL '38 days');

-- ──────────────────────────────────────────
-- Ajuste de available_copies para refletir
-- os empréstimos ACTIVE e OVERDUE acima
-- ──────────────────────────────────────────
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000001'; -- O Nome da Rosa       (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000002'; -- Cem Anos de Solidão  (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000009'; -- Fundação             (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000010'; -- Duna                 (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000017'; -- Sapiens              (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000021'; -- Código Limpo         (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000025'; -- Steve Jobs           (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000028'; -- Dom Casmurro         (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000032'; -- O Cão dos Baskervilles (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000004'; -- O Processo           (ACTIVE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000007'; -- Crime e Castigo      (OVERDUE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000016'; -- O Ser e o Nada       (OVERDUE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000036'; -- O Capital            (OVERDUE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000014'; -- Assim Falou Zaratustra (OVERDUE)
UPDATE books SET available_copies = available_copies - 1 WHERE id = 'c1000000-0000-0000-0000-000000000029'; -- Grande Sertão        (OVERDUE)
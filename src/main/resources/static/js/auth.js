// Verificar autenticação (para dashboard.html)
function verificarAutenticacao() {
    const usuarioId = localStorage.getItem('usuarioId');
    const usuarioNome = localStorage.getItem('usuarioNome');

    if (!usuarioId) {
        window.location.href = 'index.html';
        return;
    }

    usuarioAtual = { id: usuarioId, nome: usuarioNome };
    document.getElementById('usuarioInfo').textContent = `Olá, ${usuarioNome}`;
    document.getElementById('dashboardScreen').style.display = 'block';
    carregarResumo();
    carregarTransacoes();
}


function showTab(tab) {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const tabs = document.querySelectorAll('#authTabs button');

    tabs.forEach(t => t.classList.remove('active'));
    event.target.classList.add('active');

    if (tab === 'login') {
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
    } else {
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
    }
}

async function login() {
    const email = document.getElementById('loginEmail').value;
    const senha = document.getElementById('loginSenha').value;

    if (!email || !senha) {
        alert('Preencha email e senha');
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/usuarios`);
        const usuarios = await response.json();
        const usuario = usuarios.find(u => u.email === email);

        if (!usuario) {
            alert('Usuário não encontrado');
            return;
        }

        usuarioAtual = { id: usuario.id, nome: usuario.nome };
        localStorage.setItem('usuarioId', usuario.id);
        localStorage.setItem('usuarioNome', usuario.nome);

        window.location.href = 'dashboard.html';
    } catch (error) {
        alert('Erro ao fazer login: ' + error.message);
    }
}


async function register() {
    const nome = document.getElementById('registerNome').value;
    const email = document.getElementById('registerEmail').value;
    const senha = document.getElementById('registerSenha').value;

    if (!nome || !email || !senha) {
        alert('Preencha todos os campos');
        return;
    }

    if (senha.length < 6) {
        alert('Senha deve ter no mínimo 6 caracteres');
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/usuarios`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome, email, senha })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.mensagem || 'Erro ao cadastrar');
        }

        const usuario = await response.json();
        usuarioAtual = { id: usuario.id, nome: usuario.nome };
        localStorage.setItem('usuarioId', usuario.id);
        localStorage.setItem('usuarioNome', usuario.nome);

        alert('Cadastro realizado com sucesso!');
        window.location.href = 'dashboard.html';
    } catch (error) {
        alert('Erro ao cadastrar: ' + error.message);
    }
}

// Logout
function logout() {
    localStorage.removeItem('usuarioId');
    localStorage.removeItem('usuarioNome');
    usuarioAtual = null;
    window.location.href = 'index.html';
}
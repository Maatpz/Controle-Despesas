async function carregarResumo() {
    try {
        const response = await fetch(`${API_BASE}/transacoes/usuario/${usuarioAtual.id}/resumo`);
        const resumo = await response.json();
        
        document.getElementById('totalReceitas').textContent = 
            formatarMoeda(resumo.totalReceitas || 0);
        document.getElementById('totalDespesas').textContent = 
            formatarMoeda(resumo.totalDespesas || 0);
        document.getElementById('saldo').textContent = 
            formatarMoeda(resumo.saldo || 0);
        
        atualizarCorSaldo(resumo.saldo || 0);
    } catch (error) {
        console.error('Erro ao carregar resumo:', error);
    }
}

// Carregar transações
async function carregarTransacoes() {
    try {
        const filtroTipo = document.getElementById('filtroTipo').value;
        const filtroDataInicio = document.getElementById('filtroDataInicio').value;
        const filtroDataFim = document.getElementById('filtroDataFim').value;
        
        let url = `${API_BASE}/transacoes/usuario/${usuarioAtual.id}`;
        
        if (filtroTipo) {
            url = `${API_BASE}/transacoes/usuario/${usuarioAtual.id}/tipo/${filtroTipo}`;
        } else if (filtroDataInicio && filtroDataFim) {
            const dataInicio = new Date(filtroDataInicio).toISOString();
            const dataFim = new Date(filtroDataFim + 'T23:59:59').toISOString();
            url = `${API_BASE}/transacoes/usuario/${usuarioAtual.id}/periodo?dataInicio=${dataInicio}&dataFim=${dataFim}`;
        }
        
        const response = await fetch(url);
        transacoes = await response.json();
        
        renderizarTransacoes();
        carregarResumo();
    } catch (error) {
        console.error('Erro ao carregar transações:', error);
        document.getElementById('transacoesTableBody').innerHTML = 
            '<tr><td colspan="5" class="text-center text-danger py-3">Erro ao carregar transações</td></tr>';
    }
}


function renderizarTransacoes() {
    const tbody = document.getElementById('transacoesTableBody');
    
    if (transacoes.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" class="text-center py-3">Nenhuma transação encontrada</td></tr>';
        return;
    }
    
    tbody.innerHTML = transacoes.map(transacao => {
        const data = new Date(transacao.data);
        const tipoClass = transacao.tipo === 'RECEITA' ? 'warning' : 'dark';
        const tipoIcon = transacao.tipo === 'RECEITA' ? 'arrow-down' : 'arrow-up';
        const valorClass = transacao.tipo === 'RECEITA' ? 'text-warning' : 'text-dark';
        
        return `
            <tr>
                <td>${formatarData(data)}</td>
                <td>${transacao.descricao || '-'}</td>
                <td>
                    <span class="badge bg-${tipoClass}">
                        <i class="bi bi-${tipoIcon}-circle"></i> ${transacao.tipo}
                    </span>
                </td>
                <td class="${valorClass} fw-bold">${formatarMoeda(transacao.valor)}</td>
                <td class="text-center">
                    <button class="btn btn-sm btn-outline-warning me-1" onclick="editarTransacao(${transacao.id})">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-dark" onclick="deletarTransacao(${transacao.id})">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>
        `;
    }).join('');
}

// Abrir modal para nova transação
function abrirModalTransacao() {
    document.getElementById('formTransacao').reset();
    document.getElementById('transacaoId').value = '';
    document.getElementById('modalTransacaoTitle').textContent = 'Nova Transação';
    
    const now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    document.getElementById('transacaoData').value = now.toISOString().slice(0, 16);
    
    new bootstrap.Modal(document.getElementById('modalTransacao')).show();
}

// Editar transação
async function editarTransacao(id) {
    try {
        const response = await fetch(`${API_BASE}/transacoes/${id}`);
        const transacao = await response.json();
        
        document.getElementById('transacaoId').value = transacao.id;
        document.getElementById('transacaoTipo').value = transacao.tipo;
        document.getElementById('transacaoValor').value = transacao.valor;
        document.getElementById('transacaoDescricao').value = transacao.descricao || '';
        
        const data = new Date(transacao.data);
        data.setMinutes(data.getMinutes() - data.getTimezoneOffset());
        document.getElementById('transacaoData').value = data.toISOString().slice(0, 16);
        
        document.getElementById('modalTransacaoTitle').textContent = 'Editar Transação';
        new bootstrap.Modal(document.getElementById('modalTransacao')).show();
    } catch (error) {
        alert('Erro ao carregar transação: ' + error.message);
    }
}

// Salvar transação
async function salvarTransacao() {
    const form = document.getElementById('formTransacao');
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    
    const id = document.getElementById('transacaoId').value;
    const tipo = document.getElementById('transacaoTipo').value;
    const valor = parseFloat(document.getElementById('transacaoValor').value);
    const descricao = document.getElementById('transacaoDescricao').value;
    const dataStr = document.getElementById('transacaoData').value;
    
    const data = dataStr ? new Date(dataStr).toISOString() : new Date().toISOString();
    
    const transacao = {
        tipo,
        valor,
        descricao,
        data
    };
    
    try {
        let response;
        if (id) {
            response = await fetch(`${API_BASE}/transacoes/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(transacao)
            });
        } else {
            response = await fetch(`${API_BASE}/transacoes/usuario/${usuarioAtual.id}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(transacao)
            });
        }
        
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.mensagem || 'Erro ao salvar transação');
        }
        
        bootstrap.Modal.getInstance(document.getElementById('modalTransacao')).hide();
        carregarTransacoes();
        alert(id ? 'Transação atualizada!' : 'Transação criada!');
    } catch (error) {
        alert('Erro ao salvar: ' + error.message);
    }
}

// Deletar transação
async function deletarTransacao(id) {
    if (!confirm('Deseja realmente excluir esta transação?')) {
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE}/transacoes/${id}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            throw new Error('Erro ao deletar transação');
        }
        
        carregarTransacoes();
        alert('Transação excluída!');
    } catch (error) {
        alert('Erro ao deletar: ' + error.message);
    }
}
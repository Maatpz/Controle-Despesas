function formatarMoeda(valor) {
    return new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    }).format(valor);
}


function formatarData(data) {
    return new Intl.DateTimeFormat('pt-BR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    }).format(data);
}


function atualizarCorSaldo(valor) {
    const saldoEl = document.getElementById('saldo');
    if (valor >= 0) {
        saldoEl.className = 'mb-0 text-warning';
    } else {
        saldoEl.className = 'mb-0 text-danger';
    }
}
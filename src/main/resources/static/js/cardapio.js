async function listar_itens(event) {
    event.preventDefault();

    try {
        const itens = await fetch('/cardapio/exibir_cardapio')
            .then(resposta => resposta.json());

        const tabela = document.getElementById('listar_itens');
        tabela.innerHTML = ""

        itens.forEach(item =>{
            tabela.innerHTML += `
                <tr>
                    <td>${item.nome}</td>
                    <td>${item.valor.toFixed(2)}</td>
                    <td>${item.descricao}</td>
                </tr>
            `;
        });
    }catch (erro){
        alert('erro no servidor')
        throw new Error('erro no servidor')
    }
}

document.addEventListener('DOMContentLoaded', listar_itens);
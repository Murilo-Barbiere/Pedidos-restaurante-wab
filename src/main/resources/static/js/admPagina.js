async function tabela_pedidos(){
   try {
       const itens = await fetch("/pedidos/mostrar_pedidos")
           .then(resposta => resposta.json());
       const tabela = document.getElementById("listar_pedidos")

       tabela.innerHTML = "";

       itens.forEach(item => {
           tabela.innerHTML += `
                <tr>
                    <td>${item.nome_user}</td>
                    <td>${item.item_cardapio}</td>
                    <td>${item.valor.toFixed(2)}</td>
                    <td>${item.data_hora_pedido}</td>
                    <td>
                        <button 
                            onclick="pedido_feito(${item.id})">
                            pedido feito
                        </button>
                    </td>
                </tr>
            `
       });
   }catch (erro){
       console.error("Erro no servidor", erro);
   }
}

async function tabela_historico(){
    try {
        const registros = await fetch("/pedidos/mostra_historico")
            .then(historico => historico.json());
        const tabela = document.getElementById("lista_historico");

        tabela.innerHTML = "";

        registros.forEach(registro => {
            tabela.innerHTML += `
              <tr>
                <td>${registro.nome_user}</td>
                <td>${registro.item_cardapio}</td> 
                <td>${registro.valor.toFixed(2)}</td>
                <td>${registro.data_hora_pedido_feito}</td> 
                <td>${registro.data_hora_pedido_entregue}</td>
              </tr>
            `
        });

    }catch (error){
        console.error("erro no servidor", error);
    }
}

async function pedido_feito(pedido_id){
    try {
        await fetch(`pedidos/pediu/${pedido_id}`,{
            method: "DELETE"
        });

        await tabela_pedidos();
        await tabela_historico();
    }catch (error){
        console.error("erro no servidor pedido", error)
    }
}


tabela_pedidos();
tabela_historico();

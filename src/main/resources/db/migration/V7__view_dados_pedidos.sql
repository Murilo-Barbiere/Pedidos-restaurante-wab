create view dados_pedidos as
select
    p.pedidos_id as id,
    u.nome as nome_user,
    c.nome as item_cardapio,
    c.valor,
    p.data_hora_pedido
from pedidos p
         join usuario u on u.user_id = p.user_id
         join cardapio c on c.cardapio_id = p.cardapio_id
order by p.pedidos_id;

create view dados_historico as
select
    h.id_historico as id,
    u.nome as nome_user,
    c.nome as item_cardapio,
    c.valor,
    h.data_hora_pedido_feito,
    h.data_hora_pedido_entregue
from historico_pedidos_realizados_dia h
         join usuario u on u.user_id = h.user_id
         join cardapio c on c.cardapio_id = h.cardapio_id
order by h.id_historico;
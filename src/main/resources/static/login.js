function logar(event) {
    event.preventDefault();

    document.getElementById('errorMessage').innerHTML = '';

    let nome = document.getElementById('nomeLogin').value;
    let senha = document.getElementById('senhaLogin').value;

    fetch('/user/login', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nome: nome,
            senha: senha
        })
    })
            .then(resposta => {
             if(resposta.ok){
                return resposta.json();
             }
             else if(resposta.status === 401){
                 throw new Error('Usuário ou senha inválidos');
             }
             else{
                 throw new Error('Erro no servidor');
             }
        })
        .then(userData => {
            localStorage.setItem('user_nome', userData.nome);
            localStorage.setItem('user_email', userData.email);
            localStorage.setItem('user_role', userData.role);
            localStorage.setItem('user_id', userData.id);
            localStorage.setItem('is_logged_in', 'true');

            window.location.href = 'cardapio.html';
        })
        .catch(error => {
            document.getElementById('errorMessage').innerHTML = error.message;
        });
}
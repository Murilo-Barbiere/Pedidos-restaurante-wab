function logar(event) {
    event.preventDefault();

    document.getElementById('errorMessage').innerHTML = '';

    let nome = document.getElementById('nomeLogin').value;
    let senha = document.getElementById('senhaLogin').value;

    const formData = new URLSearchParams();
    formData.append('nome', nome);
    formData.append('senha', senha);

    fetch('/processar-login', {
        method: "POST",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData,
        credentials: 'include',
        redirect: 'manual'
    })
        .then(resposta => {
            if (resposta.type === 'opaqueredirect' || resposta.status === 302) {
                return fetch('/user/dados-usuario', {
                    credentials: 'include'
                });
            }
            if (!resposta.ok) {
                if (resposta.status === 401) {
                    throw new Error('Usuário ou senha inválidos');
                } else {
                    throw new Error('Erro no servidor');
                }
            }
            return resposta;
        })
        .then(resposta => {
            if (resposta && resposta.json) {
                return resposta.json();
            }
        })
        .then(userData => {
            if(userData) {
                localStorage.setItem('user_nome', userData.nome);
                localStorage.setItem('user_email', userData.email);
                localStorage.setItem('user_role', userData.role);
                localStorage.setItem('user_id', userData.id);
                localStorage.setItem('is_logged_in', 'true');

                if(userData.role === 'ROLE_ADMIN') {
                    window.location.href = 'admPagina.html';
                    return;
                }
                window.location.href = 'cardapio.html';
            }
        })
        .catch(error => {
            document.getElementById('errorMessage').innerHTML = error.message;
        });
}
function logar(event) {
    event.preventDefault();
    alert("Login realizado!");

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
            if (!resposta.ok) {
                throw new Error("Usuário não registrado");
            }
            return resposta.json();
        })
        .then(data => {
            if (data === true) {
                alert("Login realizado!");
            } else {
                alert("Usuário ou senha inválidos");
            }
        })
        .catch(error => {
            console.error("Erro:", error);
        });
}
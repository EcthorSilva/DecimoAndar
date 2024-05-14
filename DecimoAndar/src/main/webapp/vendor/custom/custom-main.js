console.log("custom-main.js carregado!");

document.addEventListener("DOMContentLoaded", function () {
    // Função para verificar se o cookie de sessão está presente
    function checkSessionCookie() {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i].trim();
            // Verifica se o cookie começa com o prefixo correto
            if (cookie.startsWith('userCookie')) {
                return true; // O cookie de sessão está presente
            }
        }
        return false; // O cookie de sessão não está presente
    }

    // Verifica se o usuário está logado antes de permitir o acesso à página
    window.onload = function() {
        // Página de perfil
        if (window.location.pathname === '/pages/profile.html') {
            if (!checkSessionCookie()) {
                // Se o usuário não estiver logado, redireciona para a página de login
                window.location.href = '/pages/login.html';
            }
        }
    }

    function updateButtonVisibility() {
        var botao1 = document.querySelector('.botao1');
        var botao2 = document.querySelector('.botao2');

        if (checkSessionCookie()) {
            // Se o cookie de sessão estiver presente, mostra o botão 2 e esconde o botão 1
            botao1.classList.add('visually-hidden');
            botao2.classList.remove('visually-hidden');
        } else {
            // Se o cookie de sessão não estiver presente, mostra o botão 1 e esconde o botão 2
            botao1.classList.remove('visually-hidden');
            botao2.classList.add('visually-hidden');
        }
    }

    // Direcionando Click do btn "Anuncie" em homepage.
    document.getElementById("AdButton").addEventListener("click", function() {

        if (checkSessionCookie()) {
            window.location.href = "/pages/imovel.html"   
        } else {  
            window.location.href = "/pages/login.html"
        }
    });

    updateButtonVisibility();

    // Adicionando a função de logout ao clique do botão de logout
    document.getElementById("logoutButton").addEventListener("click", function() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/logout", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // atualiza a pagina
                window.location.reload();
            }
        };
        xhr.send();
    });

});
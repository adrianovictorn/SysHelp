const setores = {
    "Prefeitura Municipal (PM)": "PM",
    "Secretaria Municipal de Saúde (SMS)": "SMS",
    "Secretaria Municipal de Infraestrutura (SMI)": "SEINFRA",
    "Secretaria Municipal de Transporte (SMT)": "SMT",
    "Secretaria Municipal de Educação (SME)": "SME",
    "Secretaria Municipal de Assistência Social (SMAS)": "SMAS",
    "Secretaria Municipal de Esporte e Lazer (SMEL)": "SMEL",
    "Secretaria Municipal de Planejamento (SMP)": "SMP",
    "Secretaria Municipal de Agricultura (SMA)": "SMA",
    "Secretaria Municipal de Políticas Públicas para Mulheres (SMPR)": "SMPR"
};

const tiposOcorrencia = {
    "Manutenção de Equipamento ou Sistema": "MANUTENCAO",
    "Requisição de Material": "REQUISICAO",
    "Consulta Técnica": "CONSULTA"
};

function preencherDropdown(dados, botaoId, listaId, inputId) {
    const botao = document.getElementById(botaoId);
    const lista = document.getElementById(listaId);
    const input = document.getElementById(inputId); // Certifique-se de que o elemento existe

    if (!input) {
        console.error(`Elemento com ID '${inputId}' não encontrado!`);
        return; // Impede a execução se o campo oculto não existir
    }

    lista.innerHTML = "";

    for (const [nome, codigo] of Object.entries(dados)) {
        const listItem = document.createElement("li");
        listItem.className = "dropdown-item";
        listItem.textContent = nome;
        listItem.dataset.value = codigo;

        listItem.addEventListener("click", () => {
            botao.textContent = nome;
            botao.dataset.value = codigo;
            input.value = codigo; 
            console.log("Valor atualizado em #ocorrencia:", input.value);
            lista.classList.remove("open");
        });

        lista.appendChild(listItem);
    }

    botao.addEventListener("click", () => lista.classList.toggle("open"));

    document.addEventListener("click", (event) => {
        if (!botao.contains(event.target) && !lista.contains(event.target)) {
            lista.classList.remove("open");
        }
    });
}

// Função para aplicar máscara no número de telefone
function aplicarMascaraTelefone(input) {
    let valor = input.value.replace(/\D/g, ""); // Remove tudo que não é número
    if (valor.length > 10) {
        // Formato com DDD e 9 dígitos
        valor = valor.replace(/^(\d{2})(\d{5})(\d{4})$/, "($1) $2-$3");
    } else if (valor.length > 0) {
        // Formato com DDD e 8 dígitos
        valor = valor.replace(/^(\d{2})(\d{4})(\d{0,4})$/, "($1) $2-$3");
    }
    input.value = valor;
}

// Preenche os dropdowns ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    preencherDropdown(setores, "dropdown-setor-button", "dropdown-setor-list", "setor");
    preencherDropdown(tiposOcorrencia, "dropdown-ocorrencia-button", "dropdown-ocorrencia-list", "ocorrencia");

    // Aplica máscara ao número de telefone
    const numeroInput = document.getElementById("numero");
    numeroInput.addEventListener("input", () => aplicarMascaraTelefone(numeroInput));

    // Lógica para envio do formulário
    document.getElementById("chamado-form").addEventListener("submit", async (event) => {
        event.preventDefault();

        const dados = {
            solicitante: document.getElementById("solicitante").value,
            numero: document.getElementById("numero").value,
            setor: document.getElementById("setor").value,
            ocorrencia: document.getElementById("ocorrencia").value,
            descricao: document.getElementById("descricao").value,
        };

        console.log(dados)

        try {
            const response = await fetch("http://147.93.36.85:8080/api/chamado", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(dados)
            });

            if (response.ok) {
                const result = await response.json();
                alert("Chamado enviado com sucesso! ID: " + result.id);
                window.location.href = "status-chamado.html?id=" + result.id; // Redireciona para a página de status
            } else {
                alert("Erro ao enviar o chamado. Verifique os dados e tente novamente.");
            }
        } catch (error) {
            alert("Erro ao conectar ao servidor. Tente novamente mais tarde.");
        }
    });
});

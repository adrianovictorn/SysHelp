// Função para capturar a seleção da organização
function handleSelection(event) {
    const input = event.target;
    const selectedValue = input.value;

    // Mapeamento das descrições para os códigos do enum Setores
    const mapping = {
        "Prefeitura Municipal (PM)": "PM",
        "Secretária Municipal de Saúde (SMS)": "SMS",
        "Secretária Municipal de Infraestrutura (SMI)": "SMI",
        "Secretária Municipal de Transporte (SMT)": "SMT"
    };

    // Ajusta o valor para o código correspondente
    const code = mapping[selectedValue];
    if (code) {
        input.setAttribute("data-value", code); // Salva o código no atributo data-value
    } else {
        input.removeAttribute("data-value"); // Remove o atributo se não houver correspondência
    }
}

// Enviar o formulário via JavaScript
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("chamado-form").addEventListener("submit", async function (event) {
        event.preventDefault();

        const nome = document.getElementById("solicitante").value;
        const organizacaoInput = document.getElementById("organizacao");
        const descricao = document.getElementById("descricao").value;
        const numero = document.getElementById("numero").value;

        // Obtém o código correto do setor ou exibe um alerta se inválido
        const organizacao = organizacaoInput.getAttribute("data-value");
        if (!organizacao) {
            alert("Por favor, selecione um setor válido.");
            return;
        }

        // Objeto a ser enviado
        const chamadoData = {
            solicitante: nome,
            setor: organizacao, // Envia o código correto para o backend
            descricao: descricao,
            numero: numero
        };

        try {
            const response = await fetch("http://147.93.36.85:8080/api/chamado", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(chamadoData),
            });

            if (response.ok) {
                const result = await response.json();
                alert("Chamado criado com sucesso! ID: " + result.id);
                // Redireciona para a página de status
                window.location.href = "status-chamado.html?id=" + result.id;
            } else {
                alert("Erro ao criar o chamado. Verifique os dados e tente novamente.");
            }
        } catch (error) {
            console.error("Erro ao enviar o chamado:", error);
            alert("Erro ao enviar o chamado. Tente novamente.");
        }
    });
});

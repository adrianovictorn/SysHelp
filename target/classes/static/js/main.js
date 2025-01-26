// Dados dos Departamentos
const departamentos = {
    "Prefeitura Municipal (PM)": "PM",
    "Secretaria Municipal de Saúde (SMS)": "SMS",
    "Secretaria Municipal de Infraestrutura (SMI)": "SMI",
    "Secretaria Municipal de Transporte (SMT)": "SMT",
    "Secretaria Municipal de Educação (SME)": "SME",
    "Secretaria Municipal de Assistência Social (SMAS)": "SMAS",
    "Secretaria Municipal de Esporte e Lazer (SMEL)": "SMEL",
    "Secretaria Municipal de Planejamento (SMP)": "SMP",
    "Secretaria Municipal de Agricultura (SMA)": "SMA",
    "Secretaria Municipal de Políticas Públicas para Mulheres (SMPR)": "SMPR",
    "Hospital Municipal (HM)": "HM"
};

// Dados dos Setores por Departamento
const setoresPorDepartamento = {
    "PM": [
        { nome: "Recursos Humanos (RH)", codigo: "RH" },
        { nome: "Controle Interno", codigo: "CONTROLE_INTERNO" },
        { nome: "Tributos", codigo: "TRIBUTOS" },
        { nome: "Recepção", codigo: "RECEPCAO" },
        { nome: "Gabinete", codigo: "GABINETE" },
        { nome: "Contabilidade", codigo: "CONTABILIDADE" },
        { nome: "Tesouraria", codigo: "TESOURARIA" },
        { nome: "Licitação", codigo: "LICITACAO" },
        { nome: "Convênios", codigo: "CONVENIOS" },
        { nome: "Comunicação", codigo: "COMUNICACAO" }
    ],
    //"SMS": [
     //   { nome: "Atendimento Médico", codigo: "ATENDIMENTO_MEDICO" },
       // { nome: "Farmácia", codigo: "FARMACIA" }
   // ]
};

// Dados dos Tipos de Ocorrência
const tiposOcorrencia = {
    "Manutenção de Equipamento ou Sistema": "MANUTENCAO",
    "Requisição de Material": "REQUISICAO",
    "Consulta Técnica": "CONSULTA"
};

// Função para preencher dropdowns
function preencherDropdown(dados, botaoId, listaId, inputId, callback) {
    const botao = document.getElementById(botaoId);
    const lista = document.getElementById(listaId);
    const input = document.getElementById(inputId);

    if (!botao || !lista || !input) {
        console.error(`Elementos não encontrados: ${botaoId}, ${listaId}, ${inputId}`);
        return;
    }

    lista.innerHTML = "";
    for (const [nome, codigo] of Object.entries(dados)) {
        const listItem = document.createElement("li");
        listItem.className = "dropdown-item";
        listItem.textContent = nome;
        listItem.dataset.value = codigo;

        listItem.addEventListener("click", () => {
            botao.textContent = nome;
            input.value = codigo;
            lista.classList.remove("open");
            if (callback) callback(codigo);
        });

        lista.appendChild(listItem);
    }

    botao.addEventListener("click", (e) => {
        e.stopPropagation();
        lista.classList.toggle("open");
    });

    document.addEventListener("click", () => lista.classList.remove("open"));
}

// Função para carregar setores (corrigida)
function carregarSetores(departamentoCodigo) {
    const setores = setoresPorDepartamento[departamentoCodigo] || [];
    const lista = document.getElementById("dropdown-setor-list");
    const botao = document.getElementById("dropdown-setor-button");
    const input = document.getElementById("setor");

    // Reset completo
    lista.innerHTML = "";
    input.value = "";
    lista.classList.remove("open");

    if (setores.length === 0) {
        botao.textContent = "Nenhum setor disponível";
        botao.disabled = true;
    } else {
        botao.textContent = "Selecione um setor";
        botao.disabled = false;

        setores.forEach(setor => {
            const item = document.createElement("li");
            item.className = "dropdown-item";
            item.textContent = setor.nome;
            item.dataset.value = setor.codigo;

            item.addEventListener("click", () => {
                botao.textContent = setor.nome;
                input.value = setor.codigo;
                lista.classList.remove("open");
            });

            lista.appendChild(item);
        });

        // Abre o dropdown automaticamente após carregar
        setTimeout(() => lista.classList.add("open"), 50);
    }
}

// Função para aplicar máscara no número de telefone
function aplicarMascaraTelefone(input) {
    let valor = input.value.replace(/\D/g, "");
    if (valor.length > 10) {
        valor = valor.replace(/^(\d{2})(\d{5})(\d{4})$/, "($1) $2-$3");
    } else if (valor.length > 0) {
        valor = valor.replace(/^(\d{2})(\d{4})(\d{0,4})$/, "($1) $2-$3");
    }
    input.value = valor;
}

// Inicialização
document.addEventListener("DOMContentLoaded", () => {
    preencherDropdown(
        departamentos,
        "dropdown-departamento-button",
        "dropdown-departamento-list",
        "departamento",
        carregarSetores
    );

    preencherDropdown(tiposOcorrencia, "dropdown-ocorrencia-button", "dropdown-ocorrencia-list", "ocorrencia");

    const numeroInput = document.getElementById("numero");
    numeroInput.addEventListener("input", () => aplicarMascaraTelefone(numeroInput));

    document.getElementById("chamado-form").addEventListener("submit", async (event) => {
        event.preventDefault();

        const dados = {
            solicitante: document.getElementById("solicitante").value,
            numero: document.getElementById("numero").value,
            setor: document.getElementById("setor").value,
            departamento: document.getElementById("departamento").value,
            ocorrencia: document.getElementById("ocorrencia").value,
            descricao: document.getElementById("descricao").value,
        };

        try {
            const response = await fetch("/api/chamado", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(dados)
            });

            if (response.ok) {
                const result = await response.json();
                alert("Chamado enviado com sucesso! ID: " + result.id);
                window.location.href = "status-chamado.html?id=" + result.id;
            } else {
                alert("Erro ao enviar o chamado.");
            }
        } catch {
            alert("Erro ao conectar ao servidor.");
        }
    });
});
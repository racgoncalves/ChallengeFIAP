// Teste CPF
function TestaCPF(strCPF) {

	let Soma;
	let Resto;
	Soma = 0;

	if (strCPF.length !== 11 ||
		strCPF === "00000000000" ||
		strCPF === "11111111111" ||
		strCPF === "22222222222" ||
		strCPF === "33333333333" ||
		strCPF === "44444444444" ||
		strCPF === "55555555555" ||
		strCPF === "66666666666" ||
		strCPF === "77777777777" ||
		strCPF === "88888888888" ||
		strCPF === "99999999999") {
		return false;
	}
	for (i = 1; i <= 9; i++) {
		Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
	}
	Resto = (Soma * 10) % 11;
	if ((Resto === 10) || (Resto === 11)) {
		Resto = 0;
	}
	if (Resto != parseInt(strCPF.substring(9, 10))) {
		return false;
	}
	Soma = 0;
	for (i = 1; i <= 10; i++) {
		Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
	}
	Resto = (Soma * 10) % 11;

	if ((Resto === 10) || (Resto === 11)) {
		Resto = 0;
	}
	if (Resto !== parseInt(strCPF.substring(10, 11))) {
		return false;
	}
	return true;
}


// Valida Ano
function validaAno(ano) {
	ano = parseInt(ano);
	return (ano > 1920 && ano < 2006);
}

// Valida Mês
function validaMes(mes) {
	mes = parseInt(mes);
	return (mes > 0 && mes <= 12);
}

// Dias Fevereiro
function diasFevereiro(ano) {
	ano = parseInt(ano);
	if (ano % 400 === 0) {
		return 29;
	} else if (ano % 100 !== 0 && ano % 4 === 0) {
		return 29;
	} else {
		return 28;
	}
}

// Valida Dia
function validaDia(dia, mes, ano) {
	dia = parseInt(dia);
	mes = parseInt(mes);
	ano = parseInt(ano);
	const listaMes = [31, diasFevereiro(ano), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	return (dia > 0 && dia <= listaMes[mes - 1]);
}

// Checa se ficaram campos vazios DEP
function checaVaziosDep(dep) {

	if (dep.dado01 === false) {
		$("#tipo").addClass("is-invalid");
	}

	if (dep.dado02 === false) {
		$("#nome").addClass("is-invalid");
	}

	if (dep.dado03 === false) {
		$("#cpf").addClass("is-invalid");
	}

	if (dep.dado04 === false) {
		$("#sexo").addClass("is-invalid");
	}

	if (dep.dado05 === false) {
		$("#data").addClass("is-invalid");
	}

}

// Checa se todos os dados estão corretos
function checaDados(dados) {

	for (const i in dados) {
		if (dados[i] === false) {
			return false;
		}
	}
	return true;
}

// Executa as funções
$(document).ready(function() {

	// Objeto de checagem dos dados dos dependentes

	let dep = {
		dado01: false,
		dado02: false,
		dado03: false,
		dado04: false,
		dado05: false
	}

	// Tipo de dependente

	const tipo = $("#tipo");

	tipo.change(function() {

		if (tipo.val().length !== 0) {
			$("#tipo").removeClass("is-invalid");
			$("#tipo").addClass("is-valid");
			dep.dado01 = true;
		}
	})

	// Nome

	const nome = $("#nome");

	nome.focusout(function() {

		if (nome.val().length === 0) {
			$("#nome").removeClass("is-invalid");
			$("#nome").removeClass("is-valid");
			dep.dado02 = false;
		} else {
			$("#nome").removeClass("is-invalid");
			$("#nome").addClass("is-valid");
			dep.dado02 = true;
		}
	})

	// CPF

	const cpf = $("#cpf");

	cpf.focusout(function() {

		const valorCPF = cpf.val().replace(/[^\d]+/g, '');
		const resultadoCPF = TestaCPF(valorCPF);

		if (cpf.val().length === 0) {
			$("#cpf").removeClass("is-invalid");
			$("#cpf").removeClass("is-valid");
			dep.dado03 = false;
		} else if (resultadoCPF === true) {
			$("#cpf").removeClass("is-invalid");
			$("#cpf").addClass("is-valid");
			dep.dado03 = true;
		} else {
			$("#cpf").addClass("is-invalid");
			dep.dado03 = false;
		}
	})

	// Sexo

	const sexo = $("#sexo");

	sexo.change(function() {

		if (sexo.val().length !== 0) {
			$("#sexo").removeClass("is-invalid");
			$("#sexo").addClass("is-valid");
			dep.dado04 = true;
		}
	})

	// Data de Nascimento

	const data = $("#data");

	data.focusout(function() {

		const valorData = data.val().replace(/[^\d]+/g, '');
		const resultadoAno = validaAno(valorData.substring(4, 8));
		const resultadoMes = validaMes(valorData.substring(2, 4));
		const resultadoDia = validaDia(valorData.substring(0, 2), valorData.substring(2, 4), valorData.substring(4, 8));

		if (data.val().length === 0) {
			$("#data").removeClass("is-invalid");
			$("#data").removeClass("is-valid");
			dep.dado05 = false;
		} else if (resultadoAno === true && resultadoMes === true && resultadoDia === true) {
			$("#data").removeClass("is-invalid");
			$("#data").addClass("is-valid");
			dep.dado05 = true;
		} else {
			$("#data").addClass("is-invalid");
			dep.dado05 = false;
		}
	})

	// Botão Dependente

	$("#botaoAdicionarDep").click(function() {

		// Checa se ficaram campos vazios
		checaVaziosDep(dep);

		// Checa todos os dados
		if (!checaDados(dep)) {

			return null;

		}
		else {

			$("#form_dependente").submit()

		}
	})
})

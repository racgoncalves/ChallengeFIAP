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
		$("#tipoDependente").addClass("is-invalid");
	}

	if (dep.dado02 === false) {
		$("#nomeDependente").addClass("is-invalid");
	}

	if (dep.dado03 === false) {
		$("#cpfDependente").addClass("is-invalid");
	}

	if (dep.dado04 === false) {
		$("#sexoDependente").addClass("is-invalid");
	}

	if (dep.dado05 === false) {
		$("#dataDependente").addClass("is-invalid");
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

	const tipoDep = $("#tipoDependente");

	tipoDep.change(function() {

		if (tipoDep.val().length !== 0) {
			$("#tipoDependente").removeClass("is-invalid");
			$("#tipoDependente").addClass("is-valid");
			dep.dado01 = true;
		}
	})

	// Nome Dependente

	const nomeDep = $("#nomeDependente");

	nomeDep.focusout(function() {

		if (nomeDep.val().length === 0) {
			$("#nomeDependente").removeClass("is-invalid");
			$("#nomeDependente").removeClass("is-valid");
			dep.dado02 = false;
		} else {
			$("#nomeDependente").removeClass("is-invalid");
			$("#nomeDependente").addClass("is-valid");
			dep.dado02 = true;
		}
	})

	// CPF Dependente

	const cpfDep = $("#cpfDependente");

	cpfDep.focusout(function() {

		const valorCPFDep = cpfDep.val().replace(/[^\d]+/g, '');
		const resultadoCPFDep = TestaCPF(valorCPFDep);

		if (cpfDep.val().length === 0) {
			$("#cpfDependente").removeClass("is-invalid");
			$("#cpfDependente").removeClass("is-valid");
			dep.dado03 = false;
		} else if (resultadoCPFDep === true) {
			$("#cpfDependente").removeClass("is-invalid");
			$("#cpfDependente").addClass("is-valid");
			dep.dado03 = true;
		} else {
			$("#cpfDependente").addClass("is-invalid");
			dep.dado03 = false;
		}
	})

	// Sexo Dependente

	const sexoDep = $("#sexoDependente");

	sexoDep.change(function() {

		if (sexoDep.val().length !== 0) {
			$("#sexoDependente").removeClass("is-invalid");
			$("#sexoDependente").addClass("is-valid");
			dep.dado04 = true;
		}
	})

	// Data de Nascimento Dependente

	const dataDep = $("#dataDependente");

	dataDep.focusout(function() {

		const valorDataDep = dataDep.val().replace(/[^\d]+/g, '');
		const resultadoAnoDep = validaAno(valorDataDep.substring(4, 8));
		const resultadoMesDep = validaMes(valorDataDep.substring(2, 4));
		const resultadoDiaDep = validaDia(valorDataDep.substring(0, 2), valorDataDep.substring(2, 4), valorDataDep.substring(4, 8));

		if (dataDep.val().length === 0) {
			$("#dataDependente").removeClass("is-invalid");
			$("#dataDependente").removeClass("is-valid");
			dep.dado05 = false;
		} else if (resultadoAnoDep === true && resultadoMesDep === true && resultadoDiaDep === true) {
			$("#dataDependente").removeClass("is-invalid");
			$("#dataDependente").addClass("is-valid");
			dep.dado05 = true;
		} else {
			$("#dataDependente").addClass("is-invalid");
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

			Swal.fire({
				title: 'Deseja cadastrar o dependente?',
				showDenyButton: true,
				confirmButtonText: `Cadastrar`,
				denyButtonText: `Cancelar`,
			}).then((result) => {
				if (result.isConfirmed) {
					$("#form_dependente").submit()

				}
			})
		}
	})
})

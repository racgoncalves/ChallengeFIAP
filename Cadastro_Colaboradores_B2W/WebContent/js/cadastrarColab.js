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

// Teste PIS
function TestaPIS(numeroPIS) {

	let multiplicadorBase = "3298765432";
	let total = 0;
	let resto = 0;
	let multiplicando = 0;
	let multiplicador = 0;
	let digito = 99;

	if (numeroPIS.length !== 11 ||
		numeroPIS === "00000000000" ||
		numeroPIS === "11111111111" ||
		numeroPIS === "22222222222" ||
		numeroPIS === "33333333333" ||
		numeroPIS === "44444444444" ||
		numeroPIS === "55555555555" ||
		numeroPIS === "66666666666" ||
		numeroPIS === "77777777777" ||
		numeroPIS === "88888888888" ||
		numeroPIS === "99999999999") {
		return false;
	} else {

		for (var i = 0; i < 10; i++) {
			multiplicando = parseInt(numeroPIS.substring(i, i + 1));
			multiplicador = parseInt(multiplicadorBase.substring(i, i + 1));
			total += multiplicando * multiplicador;
		}

		resto = 11 - total % 11;
		resto = resto === 10 || resto === 11 ? 0 : resto;

		digito = parseInt("" + numeroPIS.charAt(10));
		if (resto === digito) {
			return true;
		}
	}
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

// Checa se ficaram campos vazios DADOS
function checaVazios(dados) {

	if (dados.dado01 === false) {
		$("#nome").addClass("is-invalid");
	}

	if (dados.dado02 === false) {
		$("#cpf").addClass("is-invalid");
	}

	if (dados.dado03 === false) {
		$("#pis").addClass("is-invalid");
	}

	if (dados.dado04 === false) {
		$("#sexo").addClass("is-invalid");
	}

	if (dados.dado05 === false) {
		$("#nacionalidade").addClass("is-invalid");
	}

	if (dados.dado06 === false) {
		$("#naturalidade").addClass("is-invalid");
	}

	if (dados.dado07 === false) {
		$("#data").addClass("is-invalid");
	}

	if (dados.dado08 === false) {
		$("#estadoCivil").addClass("is-invalid");
	}

	if (dados.dado09 === false) {
		$("#filhos").addClass("is-invalid");
	}

	if (dados.dado10 === false) {
		$("#etnia").addClass("is-invalid");
	}

	if (dados.dado11 === false) {
		$("#camiseta").addClass("is-invalid");
	}

	if (dados.dado12 === false) {
		$("#agencia").addClass("is-invalid");
	}

	if (dados.dado13 === false) {
		$("#digitoAgencia").addClass("is-invalid");
	}

	if (dados.dado14 === false) {
		$("#conta").addClass("is-invalid");
	}

	if (dados.dado15 === false) {
		$("#digitoConta").addClass("is-invalid");
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

	// <<<<<<<<<<<<<<<<<< DADOS >>>>>>>>>>>>>>>>>>>>>>>>>

	// Objeto de checagem dos dados

	let dados = {
		dado01: false,
		dado02: false,
		dado03: false,
		dado04: false,
		dado05: false,
		dado06: false,
		dado07: false,
		dado08: false,
		dado09: false,
		dado10: false,
		dado11: false,
		dado12: false,
		dado13: false,
		dado14: false,
		dado15: false
	}

	// Nome

	const nome = $("#nome");

	nome.focusout(function() {   

		if (nome.val().length === 0) {
			$("#nome").removeClass("is-invalid");
			$("#nome").removeClass("is-valid");
			dados.dado01 = false;
		} else {
			$("#nome").removeClass("is-invalid");
			$("#nome").addClass("is-valid");
			dados.dado01 = true;
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
			dados.dado02 = false;
		} else if (resultadoCPF === true) {
			$("#cpf").removeClass("is-invalid");
			$("#cpf").addClass("is-valid");
			dados.dado02 = true;
		} else {
			$("#cpf").addClass("is-invalid");
			dados.dado02 = false;
		}
	})

	// PIS

	const pis = $("#pis");

	pis.focusout(function() {

		const valorPIS = pis.val().replace(/[^\d]+/g, '');
		const resultadoPIS = TestaPIS(valorPIS);

		if (pis.val().length === 0) {
			$("#pis").removeClass("is-invalid");
			$("#pis").removeClass("is-valid");
			dados.dado03 = false;
		} else if (resultadoPIS === true) {
			$("#pis").removeClass("is-invalid");
			$("#pis").addClass("is-valid");
			dados.dado03 = true;
		} else {
			$("#pis").addClass("is-invalid");
			dados.dado03 = false;
		}
	})

	// Sexo

	const sexo = $("#sexo");

	sexo.change(function() {

		if (sexo.val().length !== 0) {
			$("#sexo").removeClass("is-invalid");
			$("#sexo").addClass("is-valid");
			dados.dado04 = true;
		}
	})

	// Nacionalidade

	const nacionalidade = $("#nacionalidade");

	nacionalidade.focusout(function() {

		if (nacionalidade.val().length === 0) {
			$("#nacionalidade").removeClass("is-invalid");
			$("#nacionalidade").removeClass("is-valid");
			dados.dado05 = false;
		} else {
			$("#nacionalidade").removeClass("is-invalid");
			$("#nacionalidade").addClass("is-valid");
			dados.dado05 = true;
		}
	})

	// Naturalidade

	const naturalidade = $("#naturalidade");

	naturalidade.focusout(function() {

		if (naturalidade.val().length === 0) {
			$("#naturalidade").removeClass("is-invalid");
			$("#naturalidade").removeClass("is-valid");
			dados.dado06 = false;
		} else {
			$("#naturalidade").removeClass("is-invalid");
			$("#naturalidade").addClass("is-valid");
			dados.dado06 = true;
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
			dados.dado07 = false;
		} else if (resultadoAno === true && resultadoMes === true && resultadoDia === true) {
			$("#data").removeClass("is-invalid");
			$("#data").addClass("is-valid");
			dados.dado07 = true;
		} else {
			$("#data").addClass("is-invalid");
			dados.dado07 = false;
		}
	})

	// Estado Civil

	const estadoCivil = $("#estadoCivil");

	estadoCivil.change(function() {

		if (estadoCivil.val().length !== 0) {
			$("#estadoCivil").removeClass("is-invalid");
			$("#estadoCivil").addClass("is-valid");
			dados.dado08 = true;
		}
	})

	// Nº de Filhos

	const filhos = $("#filhos");

	filhos.focusout(function() {

		if (filhos.val().length === 0) {
			$("#filhos").removeClass("is-invalid");
			$("#filhos").removeClass("is-valid");
			dados.dado09 = false;
		} else {
			$("#filhos").removeClass("is-invalid");
			$("#filhos").addClass("is-valid");
			dados.dado09 = true;
		}
	})

	// Etnia

	const etnia = $("#etnia");

	etnia.change(function() {

		if (etnia.val().length !== 0) {
			$("#etnia").removeClass("is-invalid");
			$("#etnia").addClass("is-valid");
			dados.dado10 = true;
		}
	})

	// Tamanho da Camiseta

	const camiseta = $("#camiseta");

	camiseta.change(function() {

		if (camiseta.val().length !== 0) {
			$("#camiseta").removeClass("is-invalid");
			$("#camiseta").addClass("is-valid");
			dados.dado11 = true;
		}
	})

	// Nº da Agência

	const agencia = $("#agencia");

	agencia.focusout(function() {

		if (agencia.val().length === 0) {
			$("#agencia").removeClass("is-invalid");
			$("#agencia").removeClass("is-valid");
			dados.dado12 = false;
		} else if (agencia.val().length === 4) {
			$("#agencia").removeClass("is-invalid");
			$("#agencia").addClass("is-valid");
			dados.dado12 = true;
		} else {
			$("#agencia").addClass("is-invalid");
			dados.dado12 = false;
		}
	})

	// Dígito da Agência

	const digitoAgencia = $("#digitoAgencia");

	digitoAgencia.focusout(function() {

		if (digitoAgencia.val().length === 0) {
			$("#digitoAgencia").removeClass("is-invalid");
			$("#digitoAgencia").removeClass("is-valid");
			dados.dado13 = false;
		} else if (digitoAgencia.val().length === 1) {
			$("#digitoAgencia").removeClass("is-invalid");
			$("#digitoAgencia").addClass("is-valid");
			dados.dado13 = true;
		} else {
			$("#digitoAgencia").addClass("is-invalid");
			dados.dado13 = false;
		}
	})

	// Nº da Conta

	const conta = $("#conta");

	conta.focusout(function() {

		if (conta.val().length === 0) {
			$("#conta").removeClass("is-invalid");
			$("#conta").removeClass("is-valid");
			dados.dado14 = false;
		} else if (conta.val().length > 0 && conta.val().length < 8) {
			$("#conta").removeClass("is-invalid");
			$("#conta").addClass("is-valid");
			dados.dado14 = true;
		} else {
			$("#conta").addClass("is-invalid");
			dados.dado14 = false;
		}
	})

	// Dígito da Conta

	const digitoConta = $("#digitoConta");

	digitoConta.focusout(function() {

		if (digitoConta.val().length === 0) {
			$("#digitoConta").removeClass("is-invalid");
			$("#digitoConta").removeClass("is-valid");
			dados.dado15 = false;
		} else if (digitoConta.val().length === 1) {
			$("#digitoConta").removeClass("is-invalid");
			$("#digitoConta").addClass("is-valid");
			dados.dado15 = true;
		} else {
			$("#digitoConta").addClass("is-invalid");
			dados.dado15 = false;
		}
	})

	// Orientação Sexual

	// const orientacao = $("#orientacao");

	// Religião

	// const religiao = $("#religiao");

	// Botão Dados

	$("#botaoDados").click(function() {

		// Checa se ficaram campos vazios
		checaVazios(dados);

		// Checa todos os dados
		if (checaDados(dados)) {

			// Checkbox
			if ($("#check").is(":checked")) {
				$("#check").removeClass("is-invalid");
				$("#check").addClass("form-check-input");

				Swal.fire({
					title: 'Enviar os dados?',
					text: "Você não poderá retornar a esta seção depois de concluído o envio!",
					icon: 'info',
					showDenyButton: true,
					confirmButtonText: `Enviar`,
					denyButtonText: `Cancelar`,
				}).then((result) => {
					if (result.isConfirmed) {
						$("#form_dados").submit();
						}

					})

			} else {
				$("#check").addClass("is-invalid");
				return null;
			}

		}
	})
})



// Checa se ficaram campos vazios DADOS
function checaVazios(dados) {

	if (dados.dado01 === false) {
		$("#apelidoCadastro").addClass("is-invalid");
	}

	if (dados.dado02 === false) {
		$("#emailCadastro").addClass("is-invalid");
	}

	if (dados.dado03 === false) {
		$("#senhaCadastro").addClass("is-invalid");
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
	}

	// Apelido

	const apelido = $("#apelidoCadastro");

	apelido.focusout(function() {

		if (apelido.val().length === 0) {
			$("#apelidoCadastro").removeClass("is-invalid");
			$("#apelidoCadastro").removeClass("is-valid");
			dados.dado01 = false;
		} else {
			$("#apelidoCadastro").removeClass("is-invalid");
			$("#apelidoCadastro").addClass("is-valid");
			dados.dado01 = true;
		}
	})

	// E-mail

	const email = $("#emailCadastro");

	email.focusout(function() {

		if (email.val().length === 0) {
			$("#emailCadastro").removeClass("is-invalid");
			$("#emailCadastro").removeClass("is-valid");
			dados.dado02 = false;
		} else {
			$("#emailCadastro").removeClass("is-invalid");
			$("#emailCadastro").addClass("is-valid");
			dados.dado02 = true;
		}
	})

	// Senha

	const senha = $("#senhaCadastro");

	senha.focusout(function() {

		if (senha.val().length === 0) {
			$("#senhaCadastro").removeClass("is-invalid");
			$("#senhaCadastro").removeClass("is-valid");
			dados.dado03 = false;
		} else {
			$("#senhaCadastro").removeClass("is-invalid");
			$("#senhaCadastro").addClass("is-valid");
			dados.dado03 = true;
		}
	})

	// Botão Login

	$("#botaoCadastrarLogin").click(function() {

		// Checa se ficaram campos vazios
		checaVazios(dados);

		// Checa todos os dados
		if (checaDados(dados)) {

			$("#form_login").submit()

		} else {

			return null;
		}

	})
})

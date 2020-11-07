
function atualizarLogin() {

	Swal.fire({
		title: 'Deseja alterar o login?',
		showDenyButton: true,
		confirmButtonText: `Alterar`,
		denyButtonText: `Cancelar`,
	}).then((result) => {

		if (result.isConfirmed) {

			$("#form_login").submit()

		}
	})

}

// Atualizar o Login
$("#atualizarLogin").click(function() {

	atualizarLogin();

})
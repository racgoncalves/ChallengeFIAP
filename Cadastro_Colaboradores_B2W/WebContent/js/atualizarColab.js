
function atualizarColab() {

	Swal.fire({
		title: 'Deseja alterar o colaborador?',
		showDenyButton: true,
		confirmButtonText: `Alterar`,
		denyButtonText: `Cancelar`,
	}).then((result) => {

		if (result.isConfirmed) {

			$("#form_colaborador").submit()

		}
	})

}

// Atualizar o Colaborador
$("#atualizarColab").click(function() {

	atualizarColab();

})
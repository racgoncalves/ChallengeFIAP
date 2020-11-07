
function atualizarDep() {

	Swal.fire({
		title: 'Deseja alterar o dependente?',
		showDenyButton: true,
		confirmButtonText: `Alterar`,
		denyButtonText: `Cancelar`,
	}).then((result) => {

		if (result.isConfirmed) {

			$("#form_dependente").submit()

		}
	})

}

// Atualizar o Dependente
$("#botaoAtualizarDep").click(function() {

	atualizarDep();

})
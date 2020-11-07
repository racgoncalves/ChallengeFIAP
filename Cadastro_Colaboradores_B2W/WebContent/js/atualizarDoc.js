
function atualizarDoc() {

	const nomeDoc = $("#nomeDocumento");

	const nomeArq = $("#nomeArquivo");

	if (nomeDoc.val() === null) {

		Swal.fire({
			icon: 'error',
			title: 'Você não selecionou nenhum documento para alterar',
		})
	}

	else if (nomeArq.val().length === 0) {

		Swal.fire({
			icon: 'error',
			title: 'Você não incluiu nenhum arquivo para enviar',
		})

	}

	else {

		Swal.fire({
			title: 'Deseja alterar o documento?',
			showDenyButton: true,
			confirmButtonText: `Alterar`,
			denyButtonText: `Cancelar`,
		}).then((result) => {

			if (result.isConfirmed) {

				$("#form_documento").submit().then(Swal.fire({
					text: 'O documento foi alterado com sucesso!',
					icon: 'success',
					confirmButtonText: 'Fechar'
				}));

			}
		})
	}
}

// Atualizar o Documento
$("#botaoAtualizarDoc").click(function() {

	atualizarDoc();

})
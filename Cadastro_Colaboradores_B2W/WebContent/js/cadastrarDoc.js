
$(document).ready(function() {

	// Botão Documentos

	$("#botaoAdicionarDoc").click(function() {

		Swal.fire({
			title: 'Enviar os documentos?',
			text: "Você não poderá retornar a esta seção depois de concluído o envio de todos os documentos!",
			icon: 'info',
			showDenyButton: true,
			confirmButtonText: `Enviar`,
			denyButtonText: `Cancelar`,
		}).then((result) => {

			if (result.isConfirmed) {
				$("#form_documentos").submit();
			}
		})

	})
})

$(document).ready(function() {

	// Botão Finalizar

	$("#botaoFinalizar").click(function() {

		Swal.fire({
			title: 'Finalizar o cadastro?',
			text: "Você não poderá retornar a esta seção depois de finalizado!",
			icon: 'info',
			showDenyButton: true,
			confirmButtonText: `Finalizar`,
			denyButtonText: `Cancelar`,
		}).then((result) => {

			if (result.isConfirmed) {
				$("#form_finalizar").submit();
			}
		})

	})
})
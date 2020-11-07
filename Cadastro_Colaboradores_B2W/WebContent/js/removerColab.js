
function removerColab() {

	// Matrícula
	const matricula = $("#matricula");

	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/Challenge/removerLogin',
		data: {
			matricula: matricula.val(),
		}
	}).then(Swal.fire({
		text: 'O colaborador foi removido com sucesso!',
		icon: 'success',
		confirmButtonText: 'Fechar'
	}).then(setTimeout(function() {
		$("#formVoltar").submit()
	}, 1200)));

}

// Remover o Colaborador
$("#botaoRemoverColab").click(function () {

	removerColab();

})
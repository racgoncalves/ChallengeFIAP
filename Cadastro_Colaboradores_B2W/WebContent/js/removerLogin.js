
function removerLogin() {

	// Matrícula
	const matricula = $("#matricula");

	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/Challenge/removerLogin',
		data: {
			matricula: matricula.val(),
		}
	}).then(Swal.fire({
		text: 'O login foi removido com sucesso!',
		icon: 'success',
		confirmButtonText: 'Fechar'
	}).then(setTimeout(function() {
		$("#formVoltar").submit()
	}, 1200)));

};

// Remover o Login
$("#botaoRemoverLogin").click(function() {

	removerLogin();

})
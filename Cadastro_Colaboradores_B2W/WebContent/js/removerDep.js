
function removerDep() {

	// Matrícula
	const matricula = $("#matricula");

	// Código
	const codigo = $("#codigo");

	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/Challenge/removerDependente',
		data: {
			matricula: matricula.val(),
			codigo: codigo.val(),
		}
	}).then(Swal.fire({
		text: 'O dependente foi removido com sucesso!',
		icon: 'success',
		confirmButtonText: 'Fechar'
	}).then(setTimeout(function() {
		$("#formVoltar").submit()
	}, 1200)));

}

// Remover o Dependente
$("#botaoRemoverDep").click(function() {

	removerDep();

})
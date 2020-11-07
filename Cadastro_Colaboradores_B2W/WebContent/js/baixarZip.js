
// Ajax para o Zip
function baixarZip() {

	const matricula = $("#matricula");

	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/Challenge/baixarZip',
		data: {
			matricula: matricula.val()
		}
	});
	
	Swal.fire({
		text: 'O arquivo foi baixado na área de trabalho',
		icon: 'success',
		confirmButtonText: 'Fechar'
	})

}

// Baixar o Zip
$("#botaoBaixarZip").click(function() {

	baixarZip();

})
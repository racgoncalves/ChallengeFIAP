
// Ajax para o Documento
function baixarDoc() {

	const matricula = $("#matricula");
	const nomeDocumento = $("#nomeDocumento");

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Challenge/baixarDocumento',
        data: {
            matricula: matricula.val(),
			nomeDocumento: nomeDocumento.val()
        }
    });
    
    Swal.fire({
		text: 'O arquivo foi baixado na área de trabalho',
		icon: 'success',
		confirmButtonText: 'Fechar'
	})

}

// Baixar o Documento
$("#botaoBaixarDoc").click(function () {

    baixarDoc();

})
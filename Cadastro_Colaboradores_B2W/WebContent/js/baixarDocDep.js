
// Ajax para o Documento Dependente
function baixarDocDep() {

	const matricula = $("#matricula");
	const codigo = $("#codigo");

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Challenge/baixarDocumentoDep',
        data: {
            matricula: matricula.val(),
			codigo: codigo.val()
        }
    });
    
    Swal.fire({
		text: 'O arquivo foi baixado na área de trabalho',
		icon: 'success',
		confirmButtonText: 'Fechar'
	})

}

// Baixar o Documento
$("#botaoBaixarDocDep").click(function () {

    baixarDocDep();

})
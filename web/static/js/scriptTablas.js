var rowsPerPage = 5;
var currentPage = 1;


// Obtener el campo de búsqueda y la tabla
var searchInput = document.getElementById("buscador");
var table = document.getElementById("tabla2");

// Agregar un evento de entrada al campo de búsqueda
searchInput.addEventListener("input", function () {

    // Obtener el término de búsqueda y convertirlo a minúsculas
    var searchTerm = searchInput.value.toLowerCase();

    // Obtener las filas de la tabla y recorrerlas
    var rows = table.getElementsByTagName("tr");
    var foundMatch = false;
    for (var i = 0; i < rows.length; i++) {

        // Obtener las celdas de la fila y recorrerlas
        var cells = rows[i].getElementsByTagName("td");
        var shouldShowRow = false;
        for (var j = 0; j < cells.length; j++) {

            // Obtener el texto de la celda y convertirlo a minúsculas
            var cellText = cells[j].innerText.toLowerCase();

            // Si el término de búsqueda está incluido en el texto de la celda, marcar la fila como visible
            if (cellText.indexOf(searchTerm) > -1) {
                shouldShowRow = true;
                foundMatch = true;
                break;
            }
        }

        // Mostrar u ocultar la fila según corresponda
        if (shouldShowRow) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }

    // Si el campo de búsqueda está vacío, mostrar todas las filas de la tabla nuevamente
    if (searchTerm === "") {
        showPage(currentPage);
        renderPagination();
    }

    // Mostrar mensaje si no se encontraron registros
    var noRecordsMessage = document.getElementById("txtSearchFailed");
    if (foundMatch) {
        noRecordsMessage.style.display = "none";
    } else {
        noRecordsMessage.style.display = "";
    }
});

function showPage(page) {
    var rows = document.querySelectorAll("#tabla2 tbody tr");
    var startIndex = (page - 1) * rowsPerPage;
    var endIndex = startIndex + rowsPerPage;

    // Ocultar todas las filas de datos
    for (var i = 0; i < rows.length; i++) {
        rows[i].style.display = "none";
    }

    // Mostrar solo las filas de datos correspondientes a la página actual
    for (var i = startIndex; i < endIndex; i++) {
        if (rows[i]) {
            rows[i].style.display = "";
        }
    }

    // Mostrar la fila de encabezados
    var headerRow = document.querySelector("#tabla2 thead tr");
    headerRow.style.display = "";
}

function renderPagination() {
    var rows = document.querySelectorAll("#tabla2 tbody tr");
    var totalPages = Math.ceil(rows.length / rowsPerPage);

    var pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    for (var i = 1; i <= totalPages; i++) {
        var button = document.createElement("button");
        button.innerText = i;
        button.addEventListener("click", function () {
            currentPage = parseInt(this.innerText);
            showPage(currentPage);
        });
        pagination.appendChild(button);
    }
}

window.addEventListener("load", function () {
    showPage(currentPage);
    renderPagination();
});
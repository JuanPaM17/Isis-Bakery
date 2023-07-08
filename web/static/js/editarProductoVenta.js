const btnEliminar = document.getElementsByClassName('btnEliminar');
for (var i = 0; i < btnEliminar.length; i++) {
    const datos = btnEliminar[i].getAttribute('data-params').split(',');
    btnEliminar[i].addEventListener('click', eliminarProductoVenta(datos[0]));
}

const btnEditar = document.getElementsByClassName('btnEditar');
for (var i = 0; i < btnEditar.length; i++) {
    const datos = btnEditar[i].getAttribute('data-params').split(',');
    btnEditar[i].addEventListener('click', editarCantidadProducto(datos[0]));
}

function eliminarProductoVenta(item_venta) {
    return function () {
        window.location.replace("Controlador?accion=eliminarProductoVenta&nrItem=" + item_venta);
    };
}

function editarCantidadProducto(item_venta) {
    return function () {
        Swal.fire({
            title: 'Ingresa una cantidad',
            input: 'number',
            inputPlaceholder: 'Escribe un número',
            showCancelButton: true,
            confirmButtonText: 'Enviar',
            cancelButtonText: 'Cancelar',
            inputValidator: (value) => {
                const parsedValue = parseInt(value, 10);
                if (isNaN(parsedValue)) {
                    return 'Debes ingresar un número.';
                } else {
                    if (parsedValue <= 0) {
                        return 'El número debe ser mayor que cero.';
                    }
                }
            }
        }).then((result) => {
            if (result.isConfirmed) {
                const cantidad = result.value;
                window.location.replace("Controlador?accion=editarCantidadVenta&nrItem=" + item_venta + "&cantidadNueva=" + cantidad);
            }
        });
    };
}
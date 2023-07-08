const btnEliminar = document.getElementsByClassName('btnEliminar');

for (var i = 0; i < btnEliminar.length; i++) {
    const datos = btnEliminar[i].getAttribute('data-params').split(',');
    btnEliminar[i].addEventListener('click', eliminar(datos[0], datos[1], datos[2]));
}

function eliminar(id, nombre, modo) {
    return function () {
        Swal.fire({
            title: "Estas Seguro?",
            text: "Estas Seguro de eliminar (" + nombre + ") ? ",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Confirmar'
        }).then((result) => {
            if (result.isConfirmed) {
                if (modo == 1) {
                    window.location.replace("Controlador?accion=eliminarProducto&idproducto=" + id);
                }
                if (modo == 2) {
                    window.location.replace("Controlador?accion=eliminarCliente&idcliente=" + id);
                }
                if (modo == 3) {
                    window.location.replace("Controlador?accion=eliminarVendedor&idvendedor=" + id);
                }
                if (modo == 4) {
                    window.location.replace("Controlador?accion=eliminarAdministrador&idadministrador=" + id);
                }
                if (modo == 5) {
                    window.location.replace("Controlador?accion=eliminarPedido&idpedido=" + id);
                }
                if (modo == 6) {
                    window.location.replace("Controlador?accion=rechazarPedido&idpedido=" + id);
                }
            }
        });
    };
}
// Variables Globales
const catalogo = document.getElementsByClassName('catalogo');
const btnAgregarcarro = document.getElementsByClassName('btnAgregarcarro');
const inputCantidadProducto = document.getElementsByClassName('inputCantidad');
const divListaCarrito = document.getElementById('divListaCarrito');

let listaProducto = [];
let listaCarrito = [];

// Funciones
// ---> Añadir producto a la lista carrito
function añadirProductoCarro(producto, cantidad) {
    if (producto.stock < cantidad) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Solo tenemos ' + producto.stock + ' ' + producto.nombreProducto,
        });
    } else {
        let productoCarro = {
            idproducto: producto.idproducto,
            nombreProducto: producto.nombreProducto,
            precio: producto.precio,
            stock: producto.stock,
            cantidad: parseInt(cantidad),
            subTotal: (producto.precio * parseInt(cantidad, 10)).toFixed(3)
        };
        listaCarrito.push(productoCarro);
    }
}

// ---> añadir producto a la lista de los productos
function añadirProducto(id, nombre, stock, precio) {
    let precioP = precio.split('$ ');
    let producto = {
        idproducto: id,
        nombreProducto: nombre,
        stock: stock,
        precio: precioP[1]
    };
    listaProducto.push(producto);
}

// ---> Añadir el producto al carrito
function agregarProductoCarro(id) {
    return function () {
        const inputCantidadProducto = document.getElementsByName('inputCantidad' + id)[0];
        const cantidad = parseInt(inputCantidadProducto.value);
        if (cantidad <= 0 || isNaN(cantidad)) {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'Numero mayor que cero',
                showConfirmButton: false,
                timer: 1500,
                customClass: {
                    title: 'swal-title',
                    popup: 'swal-popup'
                }
            });
        } else {
            listaProducto.forEach((element) => {
                if (element.idproducto === id) {
                    if (buscarProductoCarrito(element.idproducto) === true) {
                        actualizarProducto(element, inputCantidadProducto.value);
                    } else {
                        añadirProductoCarro(element, inputCantidadProducto.value);
                    }
                    guardarLocalStorage();
                }
            });
        }

    };
}

// ---> Sumar cantidad al producto del carrito
function actualizarProducto(producto, cantidad) {
    for (let i = 0; i < listaCarrito.length; i++) {
        if (listaCarrito[i].idproducto === producto.idproducto) {
            let futuraCantidad = parseInt(listaCarrito[i].cantidad) + parseInt(cantidad);
            if (futuraCantidad > producto.stock) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Solo tenemos ' + producto.stock + ' ' + producto.nombreProducto,
                });
            } else {
                listaCarrito[i].cantidad = futuraCantidad;
                listaCarrito[i].subTotal = (producto.precio * parseInt(listaCarrito[i].cantidad, 10)).toFixed(3);
            }
            break;
        }
    }
}

// ---> Sumar cantidad al producto del carrito
function sumarCantidadCarrito(id) {
    for (let i = 0; i < listaCarrito.length; i++) {
        if (listaCarrito[i].idproducto === id) {
            let futuraCantidad = parseInt(listaCarrito[i].cantidad) + 1;
            if (futuraCantidad > listaCarrito[i].stock) {
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'Maximo de producto',
                    showConfirmButton: false,
                    timer: 1500,
                    customClass: {
                        title: 'swal-title',
                        popup: 'swal-popup'
                    }
                });
            } else {
                listaCarrito[i].cantidad += 1;
                listaCarrito[i].subTotal = (listaCarrito[i].precio * parseInt(listaCarrito[i].cantidad, 10)).toFixed(3);
            }
            break;
        }
    }
    guardarLocalStorage();
}

// ---> restar cantidad al producto del carrito
function restarCantidadCarrito(id) {
    for (let i = 0; i < listaCarrito.length; i++) {
        if (listaCarrito[i].idproducto === id) {
            let futuraCantidad = parseInt(listaCarrito[i].cantidad) - 1;
            if (futuraCantidad < 1) {
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'El minimo es un producto',
                    showConfirmButton: false,
                    timer: 1500,
                    customClass: {
                        title: 'swal-title',
                        popup: 'swal-popup'
                    }
                });
            } else {
                listaCarrito[i].cantidad -= 1;
                listaCarrito[i].subTotal = (listaCarrito[i].precio * parseInt(listaCarrito[i].cantidad, 10)).toFixed(3);
            }
            break;
        }
    }
    guardarLocalStorage();
}

// ---> Eliminar al producto del carrito
function eliminarProductoCarrito(id) {
    let indexProducto = 0;
    listaCarrito.forEach((element, index) => {
        if (element.idproducto === id) {
            indexProducto = index;
        }
    });
    listaCarrito.splice(indexProducto, 1);
    guardarLocalStorage();
}

// ---> Buscar el producto en el carrito
function buscarProductoCarrito(id) {
    for (let i = 0; i < listaCarrito.length; i++) {
        if (listaCarrito[i].idproducto === id) {
            return true;
        }
    }
    return false;
}

// CRUD LOCALSTORAGE
function guardarLocalStorage() {
    localStorage.setItem("carrito", JSON.stringify(listaCarrito));
    listarLocalStorage();
}

// AddEventListener
for (var i = 0; i < btnAgregarcarro.length; i++) {
    const datos = btnAgregarcarro[i].getAttribute('data-params').split(',');
    btnAgregarcarro[i].addEventListener('click', agregarProductoCarro(datos[0]));
}

for (var i = 0; i < catalogo.length; i++) {

    let idproducto = catalogo[i].childNodes[1].innerHTML;
    let nombreProducto = catalogo[i].childNodes[7].innerHTML;
    let stock = catalogo[i].childNodes[3].innerHTML;
    let precio = catalogo[i].childNodes[11].innerHTML;
    añadirProducto(idproducto, nombreProducto, stock, precio);
}

listarLocalStorage();

// ---> Sumar cantidad producto carro
function sumarCantidad(id) {
    return function () {
        sumarCantidadCarrito(id);
    };
}

// ---> Restar cantidad producto carro
function restarCantidad(id) {
    return function () {
        restarCantidadCarrito(id);
    };
}

// ---> Eliminar producto carro
function eliminarProducto(id) {
    return function () {
        eliminarProductoCarrito(id);
    };
}

function enviarDatos() {

    if (listaCarrito !== null && listaCarrito.length !== 0) {

        // Convertir la lista a JSON
        var jsonData = JSON.stringify(listaCarrito);

        // Crear un formulario oculto para enviar los datos al servlet
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = 'Controlador?accion=confirmarProductos'; // Reemplaza 'ServletEjemplo' con el nombre de tu servlet

        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'data';
        input.value = jsonData;

        form.appendChild(input);
        document.body.appendChild(form);

        // Enviar el formulario
        listaCarrito = [];
        localStorage.removeItem('carrito');
        form.submit();
    } else {
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'Selecione un producto',
            showConfirmButton: false,
            timer: 1500,
            customClass: {
                title: 'swal-title',
                popup: 'swal-popup'
            }
        });
    }


}

function listarLocalStorage() {
    const total = document.getElementById('total');
    const btnConfirmar = document.getElementById('btnConfirmar');
    divListaCarrito.innerHTML = "";
    listaCarrito = JSON.parse(localStorage.getItem('carrito'));
    if (listaCarrito === null) {
        listaCarrito = [];
        total.innerHTML = `Total 0.0`;
        btnConfirmar.disabled = true;
    } else {
        let totalPagar = 0;
        listaCarrito.forEach(element => {
            divListaCarrito.innerHTML += `<div class="product">
                    <div class="product-details">
                        <p> ${element.nombreProducto}</p>
                    </div>
                    <div class="product-buttons">
                        <button data-params="${element.idproducto}" class="quantity-button btnRestarCantidad">-</button>
                        <p> ${element.cantidad}</p>
                        <button data-params="${element.idproducto}" class="quantity-button btnSumarCantidad">+</button>
                        <button data-params="${element.idproducto}" class="delete-button btnEliminarProducto">Eliminar</button>
                    </div>
                    <p> SubTotal ${element.subTotal}</p>
                </div>
                <hr>`;
            totalPagar += element.precio * parseInt(element.cantidad, 10);
        });
        btnConfirmar.disabled = false;
        total.innerHTML = `Total ` + totalPagar.toFixed(3);
    }

    const btnSumarCantidad = document.getElementsByClassName('btnSumarCantidad');

    // ---> Asignar funciones a los botones de sumar cantidad
    for (var i = 0; i < btnSumarCantidad.length; i++) {
        const datos = btnSumarCantidad[i].getAttribute('data-params').split(',');
        btnSumarCantidad[i].addEventListener('click', sumarCantidad(datos[0]));
    }

    const btnRestarCantidad = document.getElementsByClassName('btnRestarCantidad');

    // ---> Asignar funciones a los botones de sumar cantidad
    for (var i = 0; i < btnRestarCantidad.length; i++) {
        const datos = btnRestarCantidad[i].getAttribute('data-params').split(',');
        btnRestarCantidad[i].addEventListener('click', restarCantidad(datos[0]));
    }

    const btnEliminarProducto = document.getElementsByClassName('btnEliminarProducto');

    // ---> Asignar funciones a los botones de eliminar
    for (var i = 0; i < btnEliminarProducto.length; i++) {
        const datos = btnEliminarProducto[i].getAttribute('data-params').split(',');
        btnEliminarProducto[i].addEventListener('click', eliminarProducto(datos[0]));
    }

}

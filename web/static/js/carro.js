document.getElementById('cart-toggle').addEventListener('click', function () {
    let listaCarrito = JSON.parse(localStorage.getItem('carrito'));
    if (listaCarrito !== null) {
        document.getElementById('cart-modal').style.display = 'block';
    }
});

document.getElementById('cart-close').addEventListener('click', function () {
    let listaCarrito = JSON.parse(localStorage.getItem('carrito'));
    if (listaCarrito !== null) {
        document.getElementById('cart-modal').style.display = 'none';
    }
});

window.addEventListener('click', function (event) {
    let listaCarrito = JSON.parse(localStorage.getItem('carrito'));
    if (listaCarrito !== null) {
        var cartModal = document.getElementById('cart-modal');
        var cartToggle = document.getElementById('cart-toggle');
        if (event.target !== cartModal && event.target !== cartToggle) {
            cartModal.style.display = 'none';
        }
    }
});

document.getElementById('cart-modal').addEventListener('click', function (event) {
    let listaCarrito = JSON.parse(localStorage.getItem('carrito'));
    if (listaCarrito !== null) {
        event.stopPropagation();
    }
});


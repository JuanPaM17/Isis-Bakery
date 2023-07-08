const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

const inputFecha = document.getElementById('fechaInput');
const inputCorreo = document.getElementById('txtCorreo');
const botonRegistrarse = document.getElementById('btnRegistro');

const fechaActual = new Date();
const fechaFormateada = fechaActual.toISOString().split('T')[0];
inputFecha.setAttribute('max', fechaFormateada);

inputCorreo.addEventListener("input", function() {
    const expresionRegular = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (expresionRegular.test(inputCorreo.value)) {
        botonRegistrarse.disabled=false;
    } else {
        botonRegistrarse.disabled=true;
    }
});

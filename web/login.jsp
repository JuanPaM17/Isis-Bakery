<!DOCTYPE html>
<html lang="es" dir="ltr">

    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <link rel="stylesheet" href="../static/css/login.css">
        <link rel="stylesheet" href="static/css/login.css">
        <link href="https://fonts.googleapis.com/css?family=Arvo" rel="stylesheet">
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="website icon" type="png" href="../static/imgIndex/logo.png">
        <link rel="website icon" type="png" href="static/imgIndex/logo.png">
        <script type="text/javascript">
            (function (c, l, a, r, i, t, y) {
                c[a] = c[a] || function () {
                    (c[a].q = c[a].q || []).push(arguments)
                };
                t = l.createElement(r);
                t.async = 1;
                t.src = "https://www.clarity.ms/tag/" + i;
                y = l.getElementsByTagName(r)[0];
                y.parentNode.insertBefore(t, y);
            })(window, document, "clarity", "script", "glcvbppp64");
        </script>
        <script type="text/javascript">
            window._mfq = window._mfq || [];
            (function () {
                var mf = document.createElement("script");
                mf.type = "text/javascript";
                mf.defer = true;
                mf.src = "//cdn.mouseflow.com/projects/0173459a-cf16-4727-8413-2f61af8a8d84.js";
                document.getElementsByTagName("head")[0].appendChild(mf);
            })();
        </script>
    </head>
    <body>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="Controlador" method="POST">
                    <h1>Crear Cuenta</h1>
                    <br>
                    <input type="number" placeholder="Cédula" name="txtCedula" value="${u.getCedula()}" required/>
                    <input type="text" placeholder="Nombre" name="txtNombre" value="${u.getNombre()}" required/>
                    <input type="Apellidos" placeholder="Apellidos" name="txtApellido" value="${u.getApellido()}" required/>
                    <input type="date" placeholder="Fecha Nacimiento" id="fechaInput" name="txtFN" value="${fecha}" required/>                 
                    <input type="number" placeholder="Celular" name="txtCelular" value="${u.getCelular()}" required/>
                    <input type="email" placeholder="Correo" id="txtCorreo" name="txtCorreo" value="${u.getCorreo()}" required/>
                    <input type="text" placeholder="Dirección" name="txtDireccion" value="${u.getDireccion()}" required/>
                    <input type="text" placeholder="Color Favorito -> Pregunta de seguridad" name="txtColor" value="${u.getColor()}" required/>
                    <input type="password" placeholder="Contraseña" name="txtContrasena" value="${u.getContrasena()}" required/>
                    <br>
                    <button disabled type="submit" name="accion" id="btnRegistro" value="registrarCliente">Registrarse</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="Controlador" method="POST">
                    <h1>Iniciar Sesión</h1>
                    <br>
                    <input type="email" placeholder="Correo" name="txtEmail" value="${corr}" required/>
                    <input type="password" placeholder="Contraseña" name="txtContra" value="${pass}" required />
                    <br>
                    <a href="recordarContrasena.jsp">¿Has olvidado la contraseña?</a>
                    <br>
                    <button type="submit" name="accion" value="inicioSesion">Iniciar Sesión</button>
                    <input type="hidden" disabled="" id="jsLogin" value="${msg}">
                </form> 
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1 class="mejoraUno">¡Bienvenido nuevamente!</h1>
                        <p class="mejoraUno">Para mantenerse conectado con nosotros, inicie sesión con sus datos personales</p>
                        <button id="signIn">Iniciar Sesión</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1 class="mejoraUno">¡Hola!</h1>
                        <p class="mejoraUno">Ingrese su información personal para tener una cuenta. </p>
                        <button class="mejoraDos" id="signUp">Registrarse</button>
                    </div>
                </div>
            </div>
        </div>
        <script defer src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="../static/js/login.js" charset="utf-8"></script>
        <script src="static/js/login.js" charset="utf-8"></script>
    </body>
    <script>
            const login = document.getElementById("jsLogin").value;
            if (login === "RegisterSuccess") {
                swal({
                    title: "Bienvenido a Isis Bakery",
                    text: "Registro exitoso",
                    icon: "success",
                    button: "Continuar"
                });
            } else {
                if (login === "RegisterError") {
                    swal({
                        title: "Ups....",
                        text: "Algo paso... Intentalo de nuevo",
                        icon: "error",
                        button: "Continuar"
                    });
                } else {
                    if (login === "InicioFailed") {
                        swal({
                            title: "Ups....",
                            text: "Correo o Contraseña incorrecta... Intentalo de nuevo",
                            icon: "error",
                            button: "Continuar"
                        });
                    } else {
                        if (login === "InicioBlock") {
                            swal({
                                title: "Ups....",
                                text: "Al parecer bloquearon tu acceso",
                                icon: "error",
                                button: "Continuar"
                            });
                        } else {
                            if (login === "OlvPass") {
                                swal({
                                    title: "Hola, <%=request.getAttribute("name")%>",
                                    text: "Tu contraseña es ( <%=request.getAttribute("pass")%> ) ! Anótala",
                                    icon: "success",
                                    button: "Continuar"
                                });
                            } else {
                                if (login === "OlvPassError") {
                                    swal({
                                        title: "Ups....",
                                        text: "Correo no encontrado",
                                        icon: "error",
                                        button: "Continuar"
                                    });
                                } else {
                                    if (login === "correoExist") {
                                        swal({
                                            title: "Ups....",
                                            text: "Ya existe alguien registrado con este correo",
                                            icon: "error",
                                            button: "Continuar"
                                        });
                                    } else {
                                        if (login === "cedulaExist") {
                                            swal({
                                                title: "Ups....",
                                                text: "Ya existe alguien registrado con esta cedula",
                                                icon: "error",
                                                button: "Continuar"
                                            });
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
    </script>
    <script defer src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</html>

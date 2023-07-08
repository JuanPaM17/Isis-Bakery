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
            <div class="form-container sign-in-container">
                <form action="Controlador" method="POST">
                    <h1>Recordar Contraseña</h1>
                    <br>
                    <input type="email" placeholder="Correo" name="txtEmail" value="${corr}" required/>
                    <br>
                    <%if (request.getAttribute("prueba") == "confirm") {
                    %>
                    <input type="text" placeholder="¿Cúal es tu color favorito?" name="txtPregunta" value="${pregunta}" required/>
                    <%                        }
                    %>
                    <br>
                    <button type="submit" name="accion" value="OlvPass">Siguiente</button>
                    <input type="hidden" disabled="" id="jsLogin" value="${msg}">
                </form> 
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1 class="mejoraUno">¡Bienvenido nuevamente!</h1>
                        <p class="mejoraUno">Para mantenerse conectado con nosotros, inicie sesión con sus datos personales</p>
                        <button id="signIn"><a href="login.jsp">Iniciar Sesión</a></button>
                    </div>
                </div>
            </div>
        </div>
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
                if (login === "questionFailed") {
                    swal({
                        title: "Ups....",
                        text: "Esa no es tu color favorito",
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
                                }
                            }
                        }
                    }
                }
            }
        </script>
        <script defer src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>
</html>

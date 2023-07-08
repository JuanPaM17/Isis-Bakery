<%
    if (session.getAttribute("SesionVendedor") != null) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            .estiloCenter{
                margin-top: 6rem;
            }
        </style>
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
    <center>
        <h1>Registrar un cliente!</h1>
    </center>

    <div class="Parteuno">
        <div class="card">
            <input type="hidden" disabled="" id="jsVende" value="${msg}">
            <form action="Controlador" method="POST">
                <div class="card-body">
                    <div class="form-group">
                        <label>Datos personales de la persona..</label>
                    </div>
                    <div class="form-group d-flex">
                        <div class="col-sm-6 d-flex">
                            <input type="number" class="form-control" value="${u.cedula}" name="txtCedulaC" required placeholder="Cedula">
                        </div>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" value="${u.nombre}" name="txtNombreC" required placeholder="Nombre Completo">
                        </div>
                    </div>

                    <div class="form-group d-flex">
                        <div class="col-sm-6 d-flex">
                            <input type="text" class="form-control" value="${u.apellido}" name="txtApellidoC" required  placeholder="Apellidos">
                        </div>

                        <div class="col-sm-6">
                            <input type="number" class="form-control" value="${u.celular}" name="txtCelularC" required  placeholder="Celular">
                        </div>
                    </div>

                    <div class="form-group d-flex">
                        <div class="col-sm-3 d-flex">
                            <input type="date" class="form-control" value="${u.fecha_nacimiento}" name="txtFNC" required placeholder="Fecha nacimiento">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" value="${u.direccion}" name="txtDirecC" required placeholder="Dirección">
                        </div>
                        <div class="col-sm-6">
                            <input type="email" class="form-control" value="${u.correo}" name="txtCorreoC" required placeholder="Correo Eléctronico">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" value="${u.color}" name="txtColorC" required placeholder="Color -> Pregunta Seguridad">
                    </div>
                    <center class="estiloCenter">
                        <div class="form-group">
                            <input type="submit" 
                                   name="accion" 
                                   value="Guardar Cliente" class="btn btn-outline-success">  
                        </div>
                    </center>
                </div>
            </form> 
        </div>
    </div>
    <script defer src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
            const regven = document.getElementById("jsVende").value;
            if (regven === "registerSuccess") {
                swal({
                    title: "Perfecto",
                    text: "Cliente registrado con exito",
                    icon: "success",
                    button: "Continuar"
                });
            } else {
                if (regven === "registerError") {
                    swal({
                        title: "Ups....",
                        text: "Algo paso... Intentalo de nuevo",
                        icon: "error",
                        button: "Continuar"
                    });
                } else {
                    if (regven === "RegisterErrorUserBD") {
                        swal({
                            title: "Ups....",
                            text: "Lo sentimos. La cedula ingresada ya existe en la base de datos. El cliente no fue creado",
                            icon: "error",
                            button: "Continuar"
                        });
                    } else {
                        if (regven === "correoExist")
                            swal({
                                title: "Ups....",
                                text: "Lo sentimos. El correo ingresado ya existe en la base de datos. El cliente no fue creado",
                                icon: "error",
                                button: "Continuar"
                            });
                    }
                }
            }
    </script>
</body>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>
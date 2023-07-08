<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("sesionCliente") != null) {
%>
<html>
    <head>
        <title>Menu Cliente</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bee51370cb.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="website icon" type="png" href="static/imgIndex/logo.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="static/css/menuCliente.css" rel="stylesheet" type="text/css"/>
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
        <header>
            <img class="logo" src="static/imgIndex/logoIsis.png" alt="logo isis" >
            <div>
                <h1 class="p-5" id="bienvenida">Bienvenido, ${nombre}
                </h1>
            </div>
            <form method="post" action="Controlador">
                <input type="hidden" name="accion" value="cerrarsesion">
                <button id="cerrar" class="btn btn-warning" type="submit">
                    <i class="fas fa-sign-out-alt"></i> Cerrar sesión
                </button>
            </form>
        </header>
        <form action="Controlador" method="POST">
            <div id="formulario"> <span id="reservar">Reservar un pedido</span>
                <label class="formulario1"> En que fecha deseas recoger el pedido</label>
                <input type="date" id="txtFR" name="txtFR" required/><br>
                <label class="formulario1">Para que hora deseas el pedido</label>
                <input type="time" name="txtHR" min="09:00" max="21:00" required/><br>
                <label class="formulario1">Seleccione los productos que desea </label>
                <button type="submit" name="accion" value="listarProductoCliente" id="seleccion"> Seleccionar productos </button>
            </div>
        </form>
        <form>
            <ul>
                <li><a href="Controlador?accion=listarProductoCliente">Productos</a>
                <li><a href="Controlador?accion=historialCliente">Historial</a>
                <li><a href="#" onclick="ayuda()">Ayuda</a>
            </ul>
        </form>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>    
    </body>
    <script>
                    var fechaInput = document.getElementById("txtFR");
                    var hoy = new Date().toISOString().split('T')[0]; //obtener la fecha actual en formato "YYYY-MM-DD"
                    fechaInput.setAttribute('min', hoy);
                    function ayuda() {
                        Swal.fire({
                            title: 'Desarrolladores',
                            html: '<b>JUAN PABLO MADRIGAL<br>SANTIAGO TORRES AGUIRRE<br>DIEGO HERNÁN GUEVARA PARRA</b><br><br>POLITÉCNICO COLOMBIANO JAIME ISAZA CADAVID',
                            imageUrl: 'imagenes/logo.jpg',
                            imageWidth: 400,
                            imageHeight: 200,
                            footer: '<a href="#">Copyright &copy; Isis Bakey Website 2022</a>',
                            imageAlt: 'Custom image',
                        });
                    }

    </script>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>

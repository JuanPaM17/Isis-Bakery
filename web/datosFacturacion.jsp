<%
    if (session.getAttribute("sesionCliente") != null) {
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>pedidos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"><script src="https://kit.fontawesome.com/bee51370cb.js" crossorigin="anonymous"></script>
        <link rel="website icon" type="png" href="static/imgIndex/logo.png">
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
            <div>
                <img class="logo" src="static/imgIndex/logoIsis.png" alt="logo isis">
                <h1 id="bienvenida"><a href="menuCliente.jsp"> Inicio </a></h1>
                <h1 id="bienvenida"> Pedido </h1>
            </div>
            <form method="post" action="Controlador">
                <input type="hidden" name="accion" value="cerrarsesion">
                <button id="cerrar" type="submit">
                    <i class="fas fa-sign-out-alt"></i> Cerrar sesión
                </button>
            </form>
        </header>
        <form action="Controlador" method="POST">
            <div id="formularioF"> <span id="reservar">Datos de Facturación</span>
                <label class="formulario1"> Nombre</label>
                <input name="txtPersonaR" type="text" value="${pedido.nombre_recoge}" placeholder="Nombre de la persona que recoge el pedido" required/><br>
                <label class="formulario1">Teléfono de contacto</label>
                <input name="txtTelCont" type="number" value="${pedido.telefono_contacto}" placeholder="Ingrese número de contacto" required/><br>
                <label class="formulario1">Valor total del Pedido </label>
                <label class="total"> ${totalPagar} </label><br>

                <%
                    Object btnConfirmar = request.getAttribute("btnConfirmar");

                    if (btnConfirmar != null) {

                %>
                <button type="submit" name="accion" value="editarPedido" id="seleccion"> Confirmar </button>
                <%                    } else {


                %>
                <button type="submit" name="accion" value="finalizarPedido" id="seleccion"> Enviar pedido </button>
                <%    }

                %>
            </div>
        </form>
        <script>
            const fecha = Document.getElementById('fecha');
            const fechaActual = new Date();
            const fechaFormateada = fechaActual.toISOString().split('T')[0];
            fecha.value += fechaFormateada;
        </script>
    </body>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>

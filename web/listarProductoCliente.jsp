<%
    if (session.getAttribute("sesionCliente") != null) {
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Productos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bee51370cb.js" crossorigin="anonymous"></script>
        <link rel="website icon" type="png" href="static/imgIndex/logo.png">
        <link href="static/css/menuCliente.css" rel="stylesheet" type="text/css"/>
        <link href="static/css/carro.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
            <div class="container">
                <img class="logo" src="static/imgIndex/logoIsis.png" alt="logo isis" >
                <h1 id="bienvenida"><a class="btn btn-primary" href="menuCliente.jsp"> Inicio </a></h1>
                <%
                    if (request.getAttribute("modo") != "observar") {
                %>
                <h1 id="bienvenida">
                    <a href="#" class="btn btn-secondary" id="cart-toggle"> Carro
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                        <div id="numeroCarro"></div>
                    </a>
                </h1> 
                <%
                    }
                %>

            </div>
            <form method="post" action="Controlador">
                <input type="hidden" name="accion" value="cerrarsesion">
                <button id="cerrar" class="btn btn-warning" type="submit">
                    <i class="fas fa-sign-out-alt"></i> Cerrar sesión
                </button>
            </form>
        </header>
        <div class="contenedorUnoImpor">
            <c:forEach var="p" items="${listaProductos}">
                <c:if test="${p.getStock()!=0}">
                    <div class="catalogo" id="r">
                        <p class="idproducto" style="display: none">${p.id_producto}</p>
                        <p class="stockproducto" style="display: none">${p.getStock()}</p>
                        <img Class="foto" src="${p.getUrlFoto()}" alt="img producto" height="200" width="200" />
                        <h2 class="nombre">${p.getNombre()}</h2>               
                        <p id="descripcion">${p.getDescripcion()}</p>
                        <p class="precio"> $ ${p.getPrecio_venta()}</p>
                        <%
                            if (request.getAttribute("modo") != "observar") {
                        %>
                        <div class="div-agregar">
                            <input type="number" name="inputCantidad${p.getId_producto()}" required>
                            <br>
                            <button type="button" data-params="${p.getId_producto()}" class="btn btn-success btnAgregarcarro">Agregar</button>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <div id="cart-modal">
            <div class="cart-content">
                <button id="cart-close">X</button>
                <h2>Carro de compras</h2>
                <p id="total"></p>  
                <div id="divListaCarrito">

                </div>
                <button type="button" id="btnConfirmar" onclick="enviarDatos()" class="btn">Confirmar</button>
            </div>
        </div>

        <%
            if (request.getAttribute("modo") != "observar") {
        %>

        <%
        } else {
        %>
        <a id="carrito" href="menuCliente.jsp" class="carri"> Me antoje de algo </a>
        <%
            }
        %>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script defer src="static/js/carro.js" type="text/javascript"></script>
        <script defer src="static/js/interactuar.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>    
    </body>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>

<%
    if (session.getAttribute("sesionCliente") != null) {
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Historial Pedidos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bee51370cb.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="static/css/menuCliente.css" rel="stylesheet" type="text/css"/>
        <<link rel="stylesheet" href="static/css/listaTablas.css"/>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="website icon" type="png" href="static/imgIndex/logo.png">
        <style>
            #pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 20px;
            }

            #pagination button {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 8px 16px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 14px;
                margin: 0 2px;
                cursor: pointer;
                border-radius: 5px;
            }

            #pagination button.active {
                background-color: #007bff;
            }

            #pagination button.disabled {
                background-color: #ccc;
                cursor: default;
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
        <header>
            <img class="logo" src="static/imgIndex/logoIsis.png" alt="logo isis">
            <div class="d-flex">
                <h1 id="bienvenida"><a href="menuCliente.jsp"> Inicio </a></h1>
                <h1 class="ms-4" id="bienvenida"> Pedidos </h1>
            </div>
            <form method="post" action="Controlador">
                <input type="hidden" name="accion" value="cerrarsesion">
                <button id="cerrar" class="btn btn-warning" type="submit">
                    <i class="fas fa-sign-out-alt"></i> Cerrar sesión
                </button>
            </form>
        </header>
    <center>
        <div id="historial"> <span id="reservar"> Historial de Pedidos </span><br><br>

            <%
                if (request.getAttribute("history") == "true") {


            %>

            <span> No has realizado ningun pedido </span>

            <%            } else {
            %>
            <div class="search-box">
                <div class="search">
                    <input class="searchPalabra" id="buscador" value="${buscar}" type="text" name="txtBuscarC" placeholder="Buscar">
                    <button class="searchButton" name="accion" value="regresarBusquedaC" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>
            </div>
            <p id="txtSearchFailed" style="display: none; font-size: 30px ">No se encontro el pedido</p>
            <input type="hidden" disabled="" id="jspeR" value="${msg}">
            <table id="tabla2" class="content-table" border="5">
                <thead>
                    <tr>
                        <th>N°Pedido</th>
                        <th>Fecha Pedido</th>
                        <th>Hora Pedido</th>
                        <th>Productos</th>
                        <th>Persona Recoge</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${listaPedidosCliente}">
                        <tr>
                            <td>${p.getId_pedido()}</td>
                            <td>${p.getFecha_recogida()}</td>
                            <td>${p.getHora_recogida()}</td>
                            <td>
                                <form action="Controlador" method="POST">
                                    <input type="hidden" name="idpedido" value="${p.getId_pedido()}"/>
                                    <button type="submit" class="btn-danger" name="accion" value="detallePedido"><i class="fa fa-exclamation" aria-hidden="true"> Ver productos</i></button><br><br>
                                </form>
                            </td>
                            <td>${p.getNombre_recoge()}</td>
                            <td>${p.getTotal()}</td>
                            <c:if test="${p.getId_estado_pedido()==1}">
                                <td>En espera</td>
                            </c:if>
                            <c:if test="${p.getId_estado_pedido()==2}">
                                <td>Confirmado</td>
                            </c:if>
                            <c:if test="${p.getId_estado_pedido()==3}">
                                <td>Rechazado</td>
                            </c:if>
                            <c:if test="${p.getId_estado_pedido()==1}">
                                <td style="text-align: center">
                                    <button class="btn-danger btnEliminar" data-params="${p.getId_pedido()},${p.getId_pedido()},5">&#x2716 Eliminar</button><br><br>
                                    <form action="Controlador" method="POST">
                                        <input type="hidden" name="idpedido" value="${p.getId_pedido()}"/>
                                        <button type="submit" class="btn-secondary" name="accion" value="pagEditarPedido">&#x2710 Editar</button>
                                    </form>
                                </td>
                            </c:if>
                            <c:if test="${p.getId_estado_pedido()==2}">
                                <td> Sin acciones </td>
                            </c:if>   
                            <c:if test="${p.getId_estado_pedido()==3}">
                                <td> Sin acciones </td>
                            </c:if>   
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="pagination">
                <!-- Los botones de paginación se agregarán aquí dinámicamente -->
            </div>
            <%
                }
            %>

        </div>
    </div>
</center>
<script>


    const pedRe = document.getElementById("jspeR").value;
    if (pedRe === "productos") {
        swal({
            title: "Detalle Productos",
            text: "<%=request.getAttribute("detallePedido")%>",
            icon: "success",
            button: "Continuar"
        });
    } else {
        if (pedRe === "editSucces") {
            swal({
                title: "Perfecto",
                text: "Tu pedido se ha editado con exito",
                icon: "success",
                button: "Continuar"
            });
        } else {
            if (pedRe === "RegistroSuccess") {
                swal({
                    title: "Perfecto",
                    text: "Tu pedido se ha realizado, espere su confirmacion",
                    icon: "success",
                    button: "Continuar"
                });
            } else {
                if (pedRe === "RegisterError") {
                    swal({
                        title: "Ups....",
                        text: "Tu pedido no se pudo realizar, intentalo de nuevo mas tarde",
                        icon: "error",
                        button: "Continuar"
                    });
                }

            }
        }
    }
</script>
<script defer src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="static/js/scriptTablas.js" type="text/javascript"></script>
<script defer src="static/js/eliminarRegistro.js" type="text/javascript"></script>
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

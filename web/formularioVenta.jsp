<%-- 
    Document   : venta
    Created on : 8/10/2022, 11:55:09 AM
    Author     : Santiago Torres
--%>
<%
    if (session.getAttribute("SesionVendedor") != null) {
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>

            @media print{
                .Parteuno, .btn, .acciones{
                    display: none;
                }
                #titPagPrint{
                    display: inline;
                }
            }
            #titPagPrint{
                color: green;
                font-weight: 800;
                display: inline;
                text-align: right;
            }
            .pp{
                margin: 0px;
            }
            body{
                background: #f2f0e9;
            }
            .Parteuno{
                width: 50%;
                margin: auto;
                padding: 10px;
            }
            .Partedos{
                width: 90%;
                margin: auto;
            }
            .title{
                font-size: 2.5rem;
                color:black;
                text-transform: uppercase;
                text-align: center;
            }
            .claseForm:valid{
                background-color: rgba(187, 197, 205, 0.541);
            }
            @media screen and (max-width:1068px){

                .Parteuno, Partedos{
                    width: 100%;
                    margin: 5px;
                }

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
        <div class="principal">
            <h3 class="title"> Realizar una venta</h3>
            <div class="Parteuno">
                <div class="card">
                    <!--<form>-->
                    <div class="card-body">
                        <div class="form-group">
                            <label>Cliente</label>
                        </div>
                        <div class="form-group d-flex">
                            <form  action="Controlador" method="POST">
                                <div class="col-sm-12 d-flex ">
                                    <input type="text" class="form-control claseForm" name="txtCedulaC"  value="${cedulaCliente}" required placeholder="Cedula">
                                    <input type="submit" name="accion" value="Buscar" class="btn btn-outline-info" >
                                </div>
                            </form>


                            <div class="col-sm-6 ">
                                <input type="text" class="form-control claseForm" readonly value="${nombreCliente}"  required placeholder="Datos del cliente" id="nombreClienteChec">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Datos de los productos</label>
                        </div>

                        <div class="form-group d-flex">
                            <form  action="Controlador" method="POST">
                                <div class="col-sm-12 d-flex ">
                                    <input type="text" class="form-control claseForm" name="codigoProducto" value="${codProductoV}" required placeholder="Codigo">
                                    <input type="submit" name="accion" value="Buscar Producto" class="btn btn-outline-info" >
                                </div>
                            </form>


                            <div class="col-sm-6 ">
                                <input type="text" class="form-control claseForm" value="${nombreProductoV}" readonly required placeholder="Datos Producto">
                            </div>
                        </div>
                        <form action="Controlador" method="POST">
                            <div class="form-group d-flex ">
                                <div class="col-sm-3 d-flex ">
                                    <input type="text" class="form-control claseForm" placeholder="Precio" readonly value="${precioProductoV}" required>
                                </div>
                                <div class="col-sm-3 ">
                                    <input type="number" class="form-control claseForm" name="nTotal" max="${stockProductoV}" required placeholder="N°">
                                </div>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control claseForm" placeholder="Stock"  value="${stockProductoV}" required readonly>
                                </div>
                            </div>

                            <div class="form-group">
                                <input type="submit" name="accion" value="Agregar Producto" class="btn btn-success">  
                            </div>
                        </form>
                        <!--<div class="form-group">
                            <input type="submit" value="Agregar" class="btn btn-success">  
                        </div>-->
                    </div>
                    <!--</form>-->
                </div>
            </div>
            <div class="Partedos">
                <div class="card">
                    <div class="card-body">
                        <div >
                            <label>Número de serie</label>
                            <input type="text" class="form-control" readonly value="${numeroSer}">
                        </div>
                        <br>
                        <table class="table table-hover col-sm-20">
                            <thead>
                                <tr>
                                    <th>NRO</th>
                                    <th>CODIGO</th>
                                    <th>DESCRPCION</th>
                                    <th>PRECIO</th>
                                    <th>CANTIDAD</th>
                                    <th>SUBTOTAL</th>
                                    <th class="acciones">ACCIONES</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pT" items="${listaProductosTabla}">
                                    <tr>
                                        <td>${pT.getNumeroVenta()}</td>
                                        <td>${pT.getCodigo()}</td>
                                        <td>${pT.getNombre()}</td>
                                        <td>${pT.getPrecio()}</td>
                                        <td>${pT.getCantidad()} </td>
                                        <td>${pT.getSubtotal()}</td>
                                        <td> 
                                            <input class="btn btn-warning btnEditar" data-params="${pT.getNumeroVenta()},${pT.getCantidad()}" type="button" value="Editar" />
                                            <input class="btn btn-danger btnEliminar" data-params="${pT.getNumeroVenta()}" type="button" value="Eliminar"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-6">
                            <form onsubmit="return validarFormulario();" action="Controlador" method="POST">
                                <input type="submit" name="accion" value="Generar Venta"  class="btn btn-success">  
                            </form>
                            <form action="Controlador" method="POST">
                                <input type="submit" name="accion" value="Cancelar Venta" class="btn btn-danger">    
                            </form>
                        </div>
                        <div class="col-sm-3 " id="titPagPrint">
                            <h4 >Total a pagar:</h4>
                        </div>

                        <div  class="col-sm-3 ml-auto">
                            <input type="text " class="form-control" value="${totPagar}" id="totPagCheck">
                        </div>
                    </div>
                </div>
                <input type="hidden" id="mensajePantalla"  value="${amensaje}"  />
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script>
                                const a = document.getElementById("mensajePantalla").value;
                                if (a === "ventaRCE") {
                                    swal({
                                        title: "Perfecto 😊",
                                        text: "La venta se realizó de manera correcta.",
                                        icon: "success",
                                        button: "Continuar"
                                    });
                                } else {
                                    if (a === "ventaCEI") {
                                        swal({
                                            title: "Listo 😲",
                                            text: "La venta se canceló...",
                                            icon: "error",
                                            button: "Continuar"
                                        });
                                    } else {
                                        if (a === "clientNoexist") {
                                            swal({
                                                title: "Error 😲",
                                                text: "El cliente no se encuentra registrado...",
                                                icon: "error",
                                                button: "Continuar"
                                            });
                                        } else {
                                            if (a === "errorVentaSinCamp") {
                                                swal({
                                                    title: "Error 😲",
                                                    text: "La venta no se pudo realizar porque faltan campos por completar",
                                                    icon: "error",
                                                    button: "Continuar"
                                                });
                                            } else {
                                                if (a === "productNoexist") {
                                                    swal({
                                                        title: "Error 😲",
                                                        text: "El producto no se encuentra registrado...",
                                                        icon: "error",
                                                        button: "Continuar"
                                                    });
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                function validarFormulario() {
                                    if (document.getElementById("nombreClienteChec").value == "" || document.getElementById("totPagCheck").value == "" || document.getElementById("totPagCheck").value == "0.0") {
                                        swal({
                                            title: "Error 😲",
                                            text: "La venta no se pudo realizar porque faltan campos por completar",
                                            icon: "error",
                                            button: "Continuar"
                                        });
                                        return false;
                                    }
                                    generarVenta();
                                    return true;
                                }

                                function generarVenta() {
                                    window.print();
                                }
        </script>
        <script defer src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script defer src="static/js/editarProductoVenta.js"></script>
    </body>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>
<!DOCTYPE html>
<%
    if (session.getAttribute("SesionAdministrador") != null) {
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modulo Productos - SB Admin</title>
        <link href="static/css/paginaAdministrador.css" rel="stylesheet" />
        <link href="static/css/listaTablas.css" rel="stylesheet" type="text/css"/>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="Controlador?accion=iniciar">Isis Bakery</a>

            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li class="dropdown-item">${nombre}</li>
                        <li class="dropdown-item">${correo}</li>
                        <li><hr class="dropdown-divider"/></li>
                        <form action="Controlador" method="POST">
                            <center>
                                <button class="btn-danger" type="submit" name="accion" value="cerrarsesion">Cerrar Sesi�n</button>
                            </center>
                        </form>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading"></div>
                            <a class="nav-link" href="Controlador?accion=iniciar">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Panel Administrativo
                            </a>
                            <div class="sb-sidenav-menu-heading">Men�</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Registros
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="Controlador?accion=listarProductos">Productos</a>
                                    <a class="nav-link" href="Controlador?accion=listarClientes">Clientes</a>
                                    <a class="nav-link" href="Controlador?accion=listarVendedores">Vendedores</a>
                                    <a class="nav-link" href="Controlador?accion=listarVentas">Ventas</a>
                                </nav>
                            </div>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                    </div>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">Reportes</div>
                            <a class="nav-link" href="Controlador?accion=listarVentasPorSemana">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Ventas
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Conectado como:</div>
                        ${perfil}
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Panel de Productos</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="Controlador?accion=iniciar">Regresar</a></li>
                            <li class="breadcrumb-item active">Panel de Productos</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                <input type="hidden" disabled="" id="NombrePro" value="${NombrePro}">
                                <input type="hidden" disabled="" id="jsProducto" value="${msg}">
                                <p class="mb-0">
                                <div class="search-box">
                                    <div class="search">
                                        <input class="searchPalabra" type="text" id="buscador" name="txtBuscarP" placeholder="Buscar" required >
                                        <button class="searchButton" type="submit"><i class="fa fa-search"></i></button>
                                    </div>
                                </div>
                                <table id="tabla2" class="content-table" border="5">
                                    <thead>
                                        <tr>
                                            <th>Codigo</th>
                                            <th>Nombre</th>
                                            <th>Descripcion</th>
                                            <th>Precio</th>
                                            <th>Stock</th>
                                            <th>Foto</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="p" items="${listaProductos}">
                                            <tr>
                                                <td>${p.getCodigo()}</td>
                                                <td>${p.getNombre()}</td>
                                                <td>${p.getDescripcion()}</td>
                                                <td>${p.getPrecio_venta()}</td>
                                                <td>${p.getStock()}</td>
                                                <td>
                                                    <img src="${p.getUrlFoto()}"  width="160" height="130" alt="Imagen/Error"/>
                                                </td>
                                                <td style="text-align: center">
                                                    <button class="btn-danger btnEliminar" data-params="${p.getId_producto()},${p.getNombre()},1">&#x2716 Eliminar</button><br><br>
                                                    <form action="Controlador" method="POST">
                                                        <input type="hidden" name="idproducto" value="${p.getId_producto()}"/>
                                                        <button type="submit" class="btn-secondary" name="accion" value="pagEditarP">&#x2710 Editar</button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div id="pagination">
                                    <!-- Los botones de paginaci�n se agregar�n aqu� din�micamente -->
                                </div><br>
                                <a type="submit" class="nva-pag" href="formularioProducto.jsp">&#x271a A�adir Producto</a>
                                </p>
                            </div>
                        </div>
                        <div style="height: 90vh"></div> 
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Isis Bakey Website 2022</div>                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="static/js/scriptTablas.js"></script>
        <script>


            const productos = document.getElementById("jsProducto").value;
            console.log(productos);
            if (productos === "editSuccess") {
                Swal.fire({
                    title: "Perfecto",
                    text: "Producto editado con exito",
                    icon: "success",
                    button: "Continuar"
                });
            } else {
                if (productos === "editFailed") {
                    swal.fire({
                        title: "Ups....",
                        text: "Algo paso... Intentalo de nuevo",
                        icon: "error",
                        button: "Continuar"
                    });
                } else {
                    if (productos === "eliminarP") {
                        Swal.fire({
                            title: "Estas Seguro?",
                            text: "Estas Seguro de eliminar (<%=request.getAttribute("NombrePro")%>) ? ",
                            icon: "warning",
                            buttons: true,
                            dangerMode: true
                        })
                                .then((willDelete) => {
                                    if (willDelete) {
                                        window.location.replace("Controlador?accion=eliminarProducto");
                                    } else {
                                        window.location.replace("Controlador?accion=listarProductos");
                                    }
                                });
                    } else {
                        if (productos === "RegisterSuccess") {
                            Swal.fire({
                                title: "Perfecto",
                                text: "Producto registrado con exito",
                                icon: "success",
                                button: "Continuar"
                            });
                        } else {
                            if (productos === "RegisterFailed") {
                                Swal.fire({
                                    title: "Ups....",
                                    text: "Algo paso... Intentalo de nuevo",
                                    icon: "error",
                                    button: "Continuar"
                                });
                            } else {
                                if (productos === "busqFailed") {
                                    Swal.fire({
                                        title: "Ups....",
                                        text: "Algo paso... No encontramos el producto",
                                        icon: "error",
                                        button: "Continuar"
                                    });
                                } else {
                                    if (productos == "avisoStock") {
                                        Swal.fire({
                                            title: "Stock....",
                                            text: "<%=request.getAttribute("aviso")%>",
                                            icon: "warning",
                                            button: "Continuar"
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
            }
        </script>
        <script defer src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="static/js/scripts.js"></script>
        <script src="static/js/eliminarRegistro.js"></script>
    </body>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>
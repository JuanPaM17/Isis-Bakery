<!DOCTYPE html>
<%
    if (session.getAttribute("SesionAdministrador") != null) {
%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modulo ventas - SB Admin</title>
        <link href="static/css/paginaAdministrador.css" rel="stylesheet" />
        <link href="static/css/listaTablas.css" rel="stylesheet" type="text/css"/>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="website icon" type="png" href="static/imgIndex/logo.png">
        <style>
            body{
                font-family: Verdana, Geneva, Tahoma, sans-serif;

            }
            h2{

                text-align: center;
            }
            .content{
                width: 60%;
                margin: 0 auto;
            }
            canvas{
                max-width: 100%;
                margin-bottom: 3rem;
            }

            @media screen and (max-width: 767px) {
                .content{
                    width: 100%;
                }
                body{
                    height: 100vh;
                }
            }
            @media screen and (min-width: 767px) and (max-width: 1023px) {
                .content{
                    width: 100%;
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
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="Controlador?accion=iniciar">Isis Bakery</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
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
                                <button class="btn-danger" type="submit" name="accion" value="cerrarsesion">Cerrar Sesión</button>
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
                            <div class="sb-sidenav-menu-heading">Menú</div>
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
                        <h1 class="mt-4">Panel de Ventas</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="Controlador?accion=iniciar">Regresar</a></li>
                            <li class="breadcrumb-item active">Reportes de ventas</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                <div class="content">
                                    <h2>N° total de ventas por semana</h2>
                                    <!-- <canvas id="myChart" height="300" width="300">
                            
                                    </canvas> -->
                                    <canvas id="myChart" width="400" height="400"></canvas>
                                    <form action="Controlador" method="GET">
                                        <input type="hidden" id="uno" value="${tot1}"/>
                                        <input type="hidden" id="dos" value="${tot2}"/>
                                        <input type="hidden" id="tres" value="${tot3}"/>
                                        <input type="hidden" id="cuatro" value="${tot4}"/>
                                        <input type="hidden" id="cinco" value="${tot5}"/>
                                        <input type="hidden" id="seis" value="${totS6}"/>
                                        <input type="hidden" id="siete" value="${tot7}"/>
                                    </form>
                                </div>
                                <div class="content">
                                    <h2>Ganancias por semana</h2>
                                    <canvas id="myChartTwo" width="400" height="400"></canvas>
                                    <form action="Controlador" method="GET">
                                        <input type="hidden" id="unoTot" value="${totSum1}"/>
                                        <input type="hidden" id="dosTot" value="${totSum2}"/>
                                        <input type="hidden" id="tresTot" value="${totSum3}"/>
                                        <input type="hidden" id="cuatroTot" value="${totSum4}"/>
                                        <input type="hidden" id="cincoTot" value="${totSum5}" />
                                        <input type="hidden" id="seisTot" value="${totSum6}"/>
                                        <input type="hidden" id="sieteTot" value="${totSum7}"/>
                                    </form>
                                </div>
                                </p>
                            </div>
                        </div>
                        <div style="height: 90vh"></div> 
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Isis Bakey Website 2022</div>                            
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="static/js/scripts.js"></script>
        <script defer src="static/js/graficaVentas.js"></script>
    </body>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>
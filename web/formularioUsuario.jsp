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
        <title>Modulo Cliente - SB Admin</title>
        <link href="static/css/paginaAdministrador.css" rel="stylesheet" />
        <link href="static/css/formularios.css" rel="stylesheet" type="text/css"/>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="website icon" type="png" href="static/imgIndex/logo.png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
                        <h1 class="mt-4">Panel de Usuario</h1>
                        <ol class="breadcrumb mb-4">
                            <%                                Object tipoUsuario = request.getAttribute("tipoU");
                                if (tipoUsuario != null) {
                                    if (tipoUsuario.equals("2")) {
                            %>
                            <li class="breadcrumb-item"><a href="Controlador?accion=listarClientes">Regresar</a></li>
                                <%                                } else {
                                    if (tipoUsuario.equals("3")) {
                                %>
                            <li class="breadcrumb-item"><a href="Controlador?accion=listarVendedores">Regresar</a></li>
                                <%  }
                                        }
                                    }
                                %>

                            <li class="breadcrumb-item active">Panel de Usuarios</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                    <br>
                                    <input type="hidden" disabled="" id="jsLogin" value="${msg}">
                                <form class="form-reg" action="Controlador" method="POST">
                                    <h1 style="text-align: center">Registrar Usuario</h1>
                                    <br>
                                    <%                                        Object btonActualizar = request.getAttribute("btnActualizar");
                                        if (btonActualizar != null) {
                                    %>      
                                    <%
                                    } else {
                                        if (tipoUsuario != null) {
                                            if (tipoUsuario.equals("2")) {
                                    %>
                                    <select style="display: none" class="select" name="txtTipoUsuario">
                                        <option value="1">Administrador</option>
                                        <option value="2" selected>Cliente</option>
                                        <option value="3">Vendedor</option>
                                    </select><br><br>
                                    <%                                            } else {
                                        if (tipoUsuario.equals("3")) {
                                    %>
                                    <select style="display: none" class="select" name="txtTipoUsuario">
                                        <option value="1">Administrador</option>
                                        <option value="2">Cliente</option>
                                        <option value="3" selected>Vendedor</option>
                                    </select><br><br>
                                    <%                                                    }
                                                }
                                            }
                                        }
                                    %>
                                    <input type="number" placeholder="Cédula" name="txtCedula" value="${u.getCedula()}" required/><br><br>
                                    <input type="text" placeholder="Nombre" name="txtNombre" value="${u.getNombre()}" required/><br><br>
                                    <input type="Apellidos" placeholder="Apellidos" name="txtApellido" value="${u.getApellido()}" required/><br><br>
                                    <input type="date" placeholder="Fecha Nacimiento" id="fechaInput" name="txtFN" value="${fecha}" required/><br><br>      
                                    <input type="number" placeholder="Celular" name="txtCelular" value="${u.getCelular()}" required/><br><br>
                                    <input type="email" placeholder="Correo" id="txtCorreo" name="txtCorreo" value="${u.getCorreo()}" required/><br><br>
                                    <input type="text" placeholder="Direccion" name="txtDirec" value="${u.getDireccion()}" required/><br><br>
                                    <input type="text" placeholder="Color -> Pregunta de seguridad" name="txtColor" value="${u.getColor()}" required/><br><br>
                                    <input type="text" placeholder="Contraseña" name="txtContrasena" value="${u.getContrasena()}" required/><br><br>
                                    <select class="select" name="txtEstado">
                                        <option value="1">Activo</option>
                                        <option value="2">Desactivo</option>
                                    </select><br><br>
                                    <br>
                                    <%
                                        if (btonActualizar != null) {
                                            if (btonActualizar.equals("true")) {
                                    %>
                                    <button class="nva-pag" type="submit" name="accion" value="editarUsuario">Confirmar</button>
                                    <a class="nva-pag" href="Controlador?accion=listarClientes">Cancelar</a>
                                    <%                                            }
                                    } else {
                                    %>
                                    <button class="nva-pag" id="btnRegistro" type="submit" name="accion" value="registrarUsuarioAdmin">Registrar</button>
                                    <%                                            }
                                    %>
                                </form>
                                <br>
                                </p>
                            </div>
                        </div>
                        <div style="height: 25vh"></div> 
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
        <script>
            const inputCorreo = document.getElementById('txtCorreo');
            const botonRegistrarse = document.getElementById('btnRegistro');
            inputCorreo.addEventListener("input", function () {
                const expresionRegular = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                if (expresionRegular.test(inputCorreo.value)) {
                    botonRegistrarse.disabled = false;
                } else {
                    botonRegistrarse.disabled = true;
                }
            });

            var fechaInput = document.getElementById("fechaInput");
            var hoy = new Date().toISOString().split('T')[0]; //obtener la fecha actual en formato "YYYY-MM-DD"
            fechaInput.setAttribute('max', hoy);

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
                                            text: "Ya existe un usuario registrado con este correo",
                                            icon: "error",
                                            button: "Continuar"
                                        });
                                    } else {
                                        if (login === "cedulaExist") {
                                            swal({
                                                title: "Ups....",
                                                text: "Ya existe un usuario registrado con esta cedula",
                                                icon: "error",
                                                button: "Continuar"
                                            });
                                        } else {
                                            if (login === "emailInvalid") {
                                                swal({
                                                    title: "Ups....",
                                                    text: "El correo no es valido",
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

            }

        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="static/js/scripts.js"></script>
    </body>
</html>
<%    } else {
        out.println("<html><head><script>window.location='index.html';</script></head><body></body></html>");
        out.close();
    }
%>
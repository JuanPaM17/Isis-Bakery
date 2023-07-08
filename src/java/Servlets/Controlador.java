/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.DetallePedido;
import Clases.Pedido;
import Clases.Producto;
import Clases.Usuario;
import Clases.Venta;
import ClasesDAO.DetalleVentaDAO;
import ClasesDAO.PedidoDAO;
import ClasesDAO.ProductoDAO;
import ClasesDAO.UsuarioDAO;
import ClasesDAO.VentaDAO;
import ClasesNecesarias.Carrito;
import ClasesNecesarias.VentaTabla;
import ClasesNecesarias.nSerie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

public class Controlador extends HttpServlet {

    // Instanciar clases DAO
    private ProductoDAO pdao = new ProductoDAO();
    private UsuarioDAO udao = new UsuarioDAO();
    private VentaDAO vdao = new VentaDAO();
    private DetalleVentaDAO dvdao = new DetalleVentaDAO();
    private PedidoDAO pedao = new PedidoDAO();
    private Carrito carrito = new Carrito();
    // Instanciar clases
    private Usuario usuario = new Usuario();
    private Producto producto = new Producto();
    private Pedido pedido = new Pedido();
    private Venta venta = new Venta();
    // ArrayList (Listas)
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private ArrayList<Usuario> listaClientes = new ArrayList<>();
    private ArrayList<Usuario> listaVendedores = new ArrayList<>();
    private ArrayList<Venta> listaVentas = new ArrayList<>();
    private ArrayList<DetallePedido> listaProductosDetalle = new ArrayList<>();
    private ArrayList<Usuario> listaVendedoresTOP = new ArrayList<>();
    private ArrayList<Pedido> listaPedidosCliente = new ArrayList<>();
    private ArrayList<Carrito> listaCarrito = new ArrayList<>();
    private ArrayList<Pedido> listaPedidosEspera = new ArrayList<>();
    private ArrayList<VentaTabla> listaVentasTabla = new ArrayList<>();
    // Variables Globales
    private Usuario sesionUsuario = new Usuario();
    private Usuario clienteBuscado = null;
    private Producto productoBuscado = null;
    private float totalPagar = 0;
    private int itemVenta = 0;
    private HttpSession session;
    private nSerie nserie = new nSerie();
    private long numeroSerie = 0;
    private float cantidadTotalAPagar = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        String accion = request.getParameter("accion");
        switch (accion) {
            // Casos Productos
            // --> 1. Listar Productos al administrador
            case "listarProductos": {
                listaProductos = pdao.listar_productos();
                String aviso = "Los siguientes productos tiene el stock vacio ------> ";
                String productos = "";
                int cont = 0;
                for (int i = 0; i < listaProductos.size(); i++) {
                    if (listaProductos.get(i).getStock() == 0) {
                        cont++;
                        productos += listaProductos.get(i).getNombre() + ", ";
                    }
                }
                if (cont > 0) {
                    request.setAttribute("msg", "avisoStock");
                    request.setAttribute("aviso", aviso + productos);
                }
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("paginaProductos.jsp").forward(request, response);
                break;
            }
            // --> 2. Eliminar un producto como administrador
            case "eliminarProducto": {
                int idProducto = Integer.parseInt(request.getParameter("idproducto"));
                //JOptionPane.showMessageDialog(null, idProducto);
                pdao.eliminar_producto(idProducto);
                listaProductos = pdao.listar_productos();
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("paginaProductos.jsp").forward(request, response);
                break;
            }
            // Casos Clientes
            // --> 1. Listar Clientes al administrador
            case "listarClientes": {
                listaClientes = udao.listar_usuarios_por_tipo(2);
                request.setAttribute("listaClientes", listaClientes);
                request.getRequestDispatcher("paginaClientes.jsp").forward(request, response);
                break;
            }
            // --> 2. Eliminar un cliente como administrador
            case "eliminarCliente": {
                int idCl = Integer.parseInt(request.getParameter("idcliente"));
                //JOptionPane.showMessageDialog(null, idCliente);
                udao.eliminar_usuario(idCl);
                listaClientes = udao.listar_usuarios_por_tipo(2);
                request.setAttribute("listaClientes", listaClientes);
                request.getRequestDispatcher("paginaClientes.jsp").forward(request, response);
                break;
            }
            // --> 3. Ir al formulario del Cliente
            case "formularioCliente": {
                request.setAttribute("tipoU", "2");
                request.getRequestDispatcher("formularioUsuario.jsp").forward(request, response);
                break;
            }
            // --> 4. Ir al historial de pedidos del cliente
            case "historialCliente": {
                listaPedidosCliente = pedao.listar_pedidos_por_cliente(sesionUsuario.getId_usuario());
                if (!listaPedidosCliente.isEmpty()) {
                    request.setAttribute("listaPedidosCliente", listaPedidosCliente);
                } else {
                    request.setAttribute("history", "true");
                }
                request.getRequestDispatcher("historialCliente.jsp").forward(request, response);
                break;
            }
            // --> 5. Mostrar los productos al cliente para realizar observar
            case "listarProductoCliente": {
                listaProductos = pdao.listar_productos();
                request.setAttribute("modo", "observar");
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("listarProductoCliente.jsp").forward(request, response);
                break;
            }
            // --> 5. El cliente le da click para seguir comprando
            case "seguirComprando": {
                listaProductos = pdao.listar_productos();
                request.setAttribute("modo", "comprar");
                request.setAttribute("carrito", listaCarrito.size());
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("listarProductoCliente.jsp").forward(request, response);
                break;
            }
            // Casos Vendedores
            // --> 1. Listar vendedores al administrador
            case "listarVendedores": {
                listaVendedores = udao.listar_usuarios_por_tipo(3);
                request.setAttribute("listaVendedores", listaVendedores);
                request.getRequestDispatcher("paginaVendedores.jsp").forward(request, response);
                break;
            }
            // --> 2. Eliminar un Vendedor como administrador
            case "eliminarVendedor": {
                int idVd = Integer.parseInt(request.getParameter("idvendedor"));
                udao.eliminar_usuario(idVd);
                listaVendedores = udao.listar_usuarios_por_tipo(3);
                request.setAttribute("listaVendedores", listaVendedores);
                request.getRequestDispatcher("paginaVendedores.jsp").forward(request, response);
                break;
            }
            // --> 3. Ir al formulario del Vendedor
            case "formularioVendedor": {
                request.setAttribute("tipoU", "3");
                request.getRequestDispatcher("formularioUsuario.jsp").forward(request, response);
                break;
            }
            // --> 4. Listar las ventas solicitadas al Vendededor
            case "ventaSolicitadas": {
                listaPedidosEspera = pedao.listar_pedidos_espera();
                listaProductosDetalle = dvdao.listar_detalle_pedido_todos();
                request.setAttribute("listaProductosDetalle", listaProductosDetalle);
                request.setAttribute("listaPedidosEspera", listaPedidosEspera);
                request.getRequestDispatcher("ventaSolicitadas.jsp").forward(request, response);
                break;
            }
            // --> 5. Rechazar un pedido como Vendedor
            case "rechazarPedido": {
                int idPedido = Integer.parseInt(request.getParameter("idpedido"));
                pedao.rechazar_pedido(idPedido);
                listaPedidosEspera = pedao.listar_pedidos_espera();
                listaProductosDetalle = dvdao.listar_detalle_pedido_todos();
                request.setAttribute("listaProductosDetalle", listaProductosDetalle);
                request.setAttribute("listaPedidosEspera", listaPedidosEspera);
                request.getRequestDispatcher("ventaSolicitadas.jsp").forward(request, response);
                break;
            }
            // --> 6. Listar productos al Vendedor
            case "listarProductoVendedor": {
                listaProductos = pdao.listar_productos();
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("listarProductoVendedor.jsp").forward(request, response);
                break;
            }
            // --> 7. Ir al formulario para registrar un cliente como Vendedor
            case "registrarCliente": {
                request.getRequestDispatcher("formularioCliente.jsp").forward(request, response);
                break;
            }
            // --> 8. Ir al formulario para realizar una venta como Vendedor
            case "formularioVenta": {
                if (clienteBuscado != null) {
                    request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                    request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                    request.setAttribute("listaProductosTabla", listaVentasTabla);
                    request.setAttribute("totPagar", cantidadTotalAPagar);
                }
                request.setAttribute("totPagar", cantidadTotalAPagar);
                numeroSerie = Long.parseLong(nserie.numeroSerie());
                request.setAttribute("numeroSer", numeroSerie);
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // --> 9. Eliminar un producto de la venta
            case "eliminarProductoVenta": {
                int nrItem = Integer.parseInt(request.getParameter("nrItem"));
                for (int i = 0; i < listaVentasTabla.size(); i++) {
                    if (nrItem == listaVentasTabla.get(i).getNumeroVenta()) {
                        cantidadTotalAPagar -= listaVentasTabla.get(i).getSubtotal();
                        listaVentasTabla.remove(i);
                        break;
                    }
                }
                itemVenta = 0;
                if (!listaVentasTabla.isEmpty()) {
                    for (int i = 0; i < listaVentasTabla.size(); i++) {
                        itemVenta++;
                        listaVentasTabla.get(i).setNumeroVenta(itemVenta);
                    }
                }
                if (clienteBuscado != null) {
                    request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                    request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                }
                request.setAttribute("totPagar", cantidadTotalAPagar);
                request.setAttribute("numeroSer", numeroSerie);
                request.setAttribute("listaProductosTabla", listaVentasTabla);
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // --> 10. Editar la cantidad de un producto de la venta
            case "editarCantidadVenta": {
                int nroItem = Integer.parseInt(request.getParameter("nrItem"));
                int cantidadNueva = Integer.parseInt(request.getParameter("cantidadNueva"));
                for (int i = 0; i < listaVentasTabla.size(); i++) {
                    if (listaVentasTabla.get(i).getNumeroVenta() == nroItem) {
                        listaVentasTabla.get(i).setCantidad(cantidadNueva);
                        listaVentasTabla.get(i).setSubtotal(listaVentasTabla.get(i).getPrecio() * cantidadNueva);
                        break;
                    }
                }
                cantidadTotalAPagar = 0;
                for (int i = 0; i < listaVentasTabla.size(); i++) {
                    cantidadTotalAPagar += listaVentasTabla.get(i).getSubtotal();
                }
                if (clienteBuscado != null) {
                    request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                    request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                }
                request.setAttribute("totPagar", cantidadTotalAPagar);
                request.setAttribute("numeroSer", numeroSerie);
                request.setAttribute("listaProductosTabla", listaVentasTabla);
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // Casos Administrador
            case "a": {
                break;
            }
            // Casos Ventas
            // --> 1. Listar ventas al administrador
            case "listarVentas": {
                listaVentas = vdao.listar_ventas();
                request.setAttribute("listaVentas", listaVentas);
                request.getRequestDispatcher("paginaVentas.jsp").forward(request, response);
                break;
            }
            // --> 2. Mostrar graficas de las venta al administrador
            case "listarVentasPorSemana": {
                for (int i = 1; i <= 7; i++) {
                    request.setAttribute("totSum" + i, vdao.ventas_semana_suma_total(i));
                    request.setAttribute("tot" + i, vdao.ventas_semana_ganancias_total(i));

                }
                request.getRequestDispatcher("paginaReporteVentas.jsp").forward(request, response);
                break;
            }
            // Casos pedidos
            // --> 1. Eliminar un pedido como cliente
            case "eliminarPedido": {
                int idCl = Integer.parseInt(request.getParameter("idpedido"));
                pedao.eliminar_pedido(idCl);
                listaPedidosCliente = pedao.listar_pedidos_por_cliente(sesionUsuario.getId_usuario());
                if (!listaPedidosCliente.isEmpty()) {
                    request.setAttribute("listaPedidosCliente", listaPedidosCliente);
                } else {
                    request.setAttribute("history", "true");
                }
                request.getRequestDispatcher("historialCliente.jsp").forward(request, response);
                break;
            }
            // Casos por defectos
            // --> 1. Ir a Pagina de inicio administrador
            case "iniciar": {
                request.setAttribute("contClient", udao.contar_usuarios_clientes());
                request.setAttribute("contVend", udao.contar_usuarios_vendedores());
                request.setAttribute("contProd", pdao.contar_produtos());
                request.setAttribute("contVenta", vdao.contar_ventas());
                request.setAttribute("espera", pedao.contar_estado_pedidos(1));
                request.setAttribute("confirmado", pedao.contar_estado_pedidos(2));
                request.setAttribute("rechazado", pedao.contar_estado_pedidos(3));
                listaVendedoresTOP = udao.top_3_vendedores_con_mas_ventas();
                for (int i = 0; i < listaVendedoresTOP.size(); i++) {
                    request.setAttribute("VendNom" + i, listaVendedoresTOP.get(i).getNombre());
                    request.setAttribute("VendVent" + i, listaVendedoresTOP.get(i).getNumero_venta());
                }
                request.getRequestDispatcher("paginaAdministrador.jsp").forward(request, response);
                break;
            }
            // --> 2. Finalizar Sesion de un usuario
            case "cerrarsesion": {
                session.invalidate();
                request.getRequestDispatcher("index.html").forward(request, response);
            }
            // En caso de errores
            default: {
                session.invalidate();
                request.getRequestDispatcher("error404.html").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        String accion = request.getParameter("accion");
        switch (accion) {
            // Casos Productos
            // --> 1. Listar Productos al usuario anononimo
            case "productosPanederia": {
                listaProductos = pdao.listar_productos();
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("catalogoProductos.jsp").forward(request, response);
                break;
            }
            // --> 2. Registrar producto como administrador
            case "registrarProducto": {
                String codigo = request.getParameter("txtCodigoP");
                String nombre = request.getParameter("txtNombreP"); //Trae el input que tiene como name txtNombre
                String descripcion = request.getParameter("txtDescP");
                float precio = Float.parseFloat(request.getParameter("txtPrecioP"));
                int stock = Integer.parseInt(request.getParameter("txtStockP"));
                String foto = request.getParameter("imgFotoP");
                producto = new Producto(codigo, nombre, descripcion, precio, stock, foto);
                if (pdao.registro_producto(producto)) {
                    // Registro Exitoso
                    request.setAttribute("msg", "RegisterSuccess");
                } else {
                    // Registro Fallido
                    request.setAttribute("msg", "RegisterFailed");
                }
                listaProductos = pdao.listar_productos();
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("paginaProductos.jsp").forward(request, response);
                break;
            }
            // --> 3. Ir a la pagina editar Producto
            case "pagEditarP": {
                producto.setId_producto(Integer.parseInt(request.getParameter("idproducto")));
                producto = pdao.buscar_producto_por_Id(producto);
                if (producto.getCodigo() == null) {
                    // No encontro el producto
                    listaProductos = pdao.listar_productos();
                    request.setAttribute("listaProductos", listaProductos);
                    request.getRequestDispatcher("paginaProductos.jsp").forward(request, response);
                } else {
                    // Encontro el producto
                    request.setAttribute("btnActualizar", "true");
                    request.setAttribute("p", producto);
                    request.getRequestDispatcher("formularioProducto.jsp").forward(request, response);
                }
                break;
            }
            // --> 4. Editar el producto
            case "editarProducto": {
                producto.setCodigo(request.getParameter("txtCodigoP"));
                producto.setNombre(request.getParameter("txtNombreP")); //Trae el input que tiene como name txtNombre
                producto.setDescripcion(request.getParameter("txtDescP"));
                producto.setPrecio_venta(Float.parseFloat(request.getParameter("txtPrecioP")));
                producto.setStock(Integer.parseInt(request.getParameter("txtStockP")));
                producto.setUrlFoto(request.getParameter("imgFotoP"));
                if (pdao.actualizar_producto(producto)) {
                    // Edicion Exitosa
                    request.setAttribute("msg", "editSuccess");
                } else {
                    // Edicion Fallida
                    request.setAttribute("msg", "editFailed");
                }
                listaProductos = pdao.listar_productos();
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("paginaProductos.jsp").forward(request, response);
                break;
            }
            // Casos Usuarios
            // --> 1. Un Usuario se registra en IsisBakery
            case "registrarCliente": {
                String fecha_N = request.getParameter("txtFN");
                long cedula = Long.parseLong(request.getParameter("txtCedula"));
                String nombre = request.getParameter("txtNombre"); //Trae el input que tiene como name txtNombre
                String apellido = request.getParameter("txtApellido");
                long celular = Long.parseLong(request.getParameter("txtCelular"));
                String correo = request.getParameter("txtCorreo");
                String direccion = request.getParameter("txtDireccion");
                String color = request.getParameter("txtColor");
                String contrasena = request.getParameter("txtContrasena");
                usuario = new Usuario(cedula, nombre, apellido, fecha_N, celular, correo, direccion, color, contrasena, 1, 2);
                if (udao.verificar_correo_usuario(usuario)) {
                    // El correo ya esta registrado
                    request.setAttribute("fecha", fecha_N);
                    request.setAttribute("u", usuario);
                    request.setAttribute("msg", "correoExist");
                } else {
                    if (udao.verificar_cedula_usuario(usuario)) {
                        // La cedula ya esta registrada
                        request.setAttribute("fecha", fecha_N);
                        request.setAttribute("u", usuario);
                        request.setAttribute("msg", "cedulaExist");
                    } else {
                        if (udao.registro_usuario(usuario)) {
                            // Registro Exitoso
                            request.setAttribute("msg", "RegisterSuccess");
                        } else {
                            // Algun error al registrar
                            request.setAttribute("msg", "RegisterError");
                            request.setAttribute("fecha", fecha_N);
                            request.setAttribute("u", usuario);
                        }
                    }
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            }
            // --> 2. Un Usuario inicia sesion en IsisBakery
            case "inicioSesion": {
                usuario = new Usuario();
                usuario.setCorreo(request.getParameter("txtEmail"));
                usuario.setContrasena(request.getParameter("txtContra"));
                usuario = udao.inicio_sesion_usuario(usuario);
                if (usuario != null) {
                    // El usuario existe
                    sesionUsuario = usuario;
                    if (usuario.getId_estado_usuario() != 2) {
                        // Tiene el inicio disponible
                        if (usuario.getId_tipo_usuario() == 1) {
                            // Inicio Admin
                            session.setAttribute("SesionAdministrador", sesionUsuario);
                            session.setAttribute("nombre", sesionUsuario.getNombre());
                            session.setAttribute("correo", sesionUsuario.getCorreo());
                            session.setAttribute("perfil", "Administrador");
                            request.setAttribute("contClient", udao.contar_usuarios_clientes());
                            request.setAttribute("contVend", udao.contar_usuarios_vendedores());
                            request.setAttribute("contProd", pdao.contar_produtos());
                            request.setAttribute("contVenta", vdao.contar_ventas());
                            request.setAttribute("espera", pedao.contar_estado_pedidos(1));
                            request.setAttribute("confirmado", pedao.contar_estado_pedidos(2));
                            request.setAttribute("rechazado", pedao.contar_estado_pedidos(3));
                            listaVendedoresTOP = udao.top_3_vendedores_con_mas_ventas();
                            for (int i = 0; i < listaVendedoresTOP.size(); i++) {
                                request.setAttribute("VendNom" + i, listaVendedoresTOP.get(i).getNombre());
                                request.setAttribute("VendVent" + i, listaVendedoresTOP.get(i).getNumero_venta());
                            }
                            request.getRequestDispatcher("paginaAdministrador.jsp").forward(request, response);
                        }
                        if (usuario.getId_tipo_usuario() == 2) {
                            // Inicio Cliente
                            listaCarrito = new ArrayList<>();
                            session.setAttribute("sesionCliente", sesionUsuario);
                            session.setAttribute("nombre", sesionUsuario.getNombre() + " " + sesionUsuario.getApellido());
                            request.getRequestDispatcher("menuCliente.jsp").forward(request, response);
                        }
                        if (usuario.getId_tipo_usuario() == 3) {
                            // Inicio Vendedor
                            session.setAttribute("SesionVendedor", sesionUsuario);
                            session.setAttribute("nombreC", sesionUsuario.getNombre() + " " + sesionUsuario.getApellido() + " ");
                            session.setAttribute("nombre", sesionUsuario.getNombre());
                            session.setAttribute("correo", sesionUsuario.getCorreo());
                            request.getRequestDispatcher("menuVendedor.jsp").forward(request, response);
                        }
                    } else {
                        // El usuario tiene el inicio bloqueado
                        request.setAttribute("corr", usuario.getCorreo());
                        request.setAttribute("pass", usuario.getContrasena());
                        request.setAttribute("msg", "InicioBlock");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    // Algun error al iniciar sesion
                    request.setAttribute("corr", request.getParameter("txtEmail"));
                    request.setAttribute("pass", request.getParameter("txtContra"));
                    request.setAttribute("msg", "InicioFailed");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                break;
            }
            // --> 3. Un Usuario recupera su contraseña
            case "OlvPass": {
                String correo = request.getParameter("txtEmail");
                String pregunta = request.getParameter("txtPregunta");
                usuario = udao.olvido_contra_usuario(correo);
                if (usuario != null) {
                    // El usuario si existe
                    if (pregunta == null) {
                        // El usuario no responde la pregunta de recuperacion
                        request.setAttribute("corr", usuario.getCorreo());
                        request.setAttribute("prueba", "confirm");
                        request.getRequestDispatcher("recordarContrasena.jsp").forward(request, response);
                    } else {
                        // El usuario si responde la pregunta de recuperacion
                        if (pregunta.equals(usuario.getColor())) {
                            // El usuario recupera su contraseña
                            request.setAttribute("name", usuario.getNombre());
                            request.setAttribute("pass", usuario.getContrasena());
                            request.setAttribute("corr", usuario.getCorreo());
                            request.setAttribute("msg", "OlvPass");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        } else {
                            // El usuario no recupera su contraseña
                            request.setAttribute("prueba", "confirm");
                            request.setAttribute("corr", correo);
                            request.setAttribute("pregunta", pregunta);
                            request.setAttribute("msg", "questionFailed");
                            request.getRequestDispatcher("recordarContrasena.jsp").forward(request, response);
                        }
                    }
                } else {
                    // El usuario no existe
                    request.setAttribute("corr", correo);
                    request.setAttribute("msg", "OlvPassError");
                    request.getRequestDispatcher("recordarContrasena.jsp").forward(request, response);
                }
                break;
            }
            // --> 4. Registrar un usuario como Administrador
            case "registrarUsuarioAdmin": {
                long cedula = Long.parseLong(request.getParameter("txtCedula"));
                String nombre = request.getParameter("txtNombre");
                String apellido = request.getParameter("txtApellido");
                String fecha_nacimiento = request.getParameter("txtFN");
                long celular = Long.parseLong(request.getParameter("txtCelular"));
                String correo = request.getParameter("txtCorreo");
                String direccion = request.getParameter("txtDirec");
                String color = request.getParameter("txtColor");
                String contrasena = request.getParameter("txtContrasena");
                int estado_usuario = Integer.parseInt(request.getParameter("txtEstado"));
                int tipo_usuario = Integer.parseInt(request.getParameter("txtTipoUsuario"));
                usuario = new Usuario(cedula, nombre, apellido, fecha_nacimiento, celular, correo, direccion, color, contrasena, estado_usuario, tipo_usuario);
                if (udao.verificar_correo_usuario(usuario)) {
                    // El correo ya esta registrado
                    request.setAttribute("msg", "correoExist");
                    request.setAttribute("fecha", fecha_nacimiento);
                    request.setAttribute("u", usuario);
                    request.getRequestDispatcher("formularioUsuario.jsp").forward(request, response);
                } else {
                    if (udao.verificar_cedula_usuario(usuario)) {
                        // La cedula ya esta registrada
                        request.setAttribute("fecha", fecha_nacimiento);
                        request.setAttribute("u", usuario);
                        request.setAttribute("msg", "cedulaExist");
                        request.getRequestDispatcher("formularioUsuario.jsp").forward(request, response);
                    } else {
                        if (udao.registro_usuario(usuario)) {
                            // Registro Exitoso
                            request.setAttribute("msg", "RegisterSuccess");
                            if (usuario.getId_tipo_usuario() == 1) {
                                // Cosas para el administrador

                            }
                            if (usuario.getId_tipo_usuario() == 2) {
                                // Cosas para el cliente
                                listaClientes = udao.listar_usuarios_por_tipo(2);
                                request.setAttribute("listaClientes", listaClientes);
                                request.getRequestDispatcher("paginaClientes.jsp").forward(request, response);
                            }
                            if (usuario.getId_tipo_usuario() == 3) {
                                // Cosas para el Vendedor
                                listaVendedores = udao.listar_usuarios_por_tipo(3);
                                request.setAttribute("listaVendedores", listaVendedores);
                                request.getRequestDispatcher("paginaVendedores.jsp").forward(request, response);
                            }
                        } else {
                            //Algun error al registrar
                            request.setAttribute("msg", "RegisterError");
                            request.setAttribute("fecha", fecha_nacimiento);
                            request.setAttribute("u", usuario);
                            request.getRequestDispatcher("formularioUsuario.jsp").forward(request, response);
                        }
                    }
                }
                break;
            }
            // --> 5. Ir a la pagina editar Usuario
            case "pagEditarU": {
                usuario.setId_usuario(Integer.parseInt(request.getParameter("idcliente")));
                usuario = udao.buscar_usuario_por_Id(usuario);
                //JOptionPane.showMessageDialog(null, usuario.toString());
                if (usuario.getColor() == null) {
                    // No se encontro el usuario
                    listaClientes = udao.listar_usuarios_por_tipo(2);
                    request.setAttribute("listaClientes", listaClientes);
                    request.getRequestDispatcher("paginaClientes.jsp").forward(request, response);
                } else {
                    // Se encontro el usuario
                    request.setAttribute("btnActualizar", "true");
                    request.setAttribute("u", usuario);
                    request.setAttribute("fecha", usuario.getFecha_nacimiento());
                    request.getRequestDispatcher("formularioUsuario.jsp").forward(request, response);
                }
                break;
            }
            // --> 6. Editar el Usuario
            case "editarUsuario": {
                usuario.setCedula(Long.parseLong(request.getParameter("txtCedula")));
                usuario.setNombre(request.getParameter("txtNombre"));
                usuario.setApellido(request.getParameter("txtApellido"));
                usuario.setFecha_nacimiento(request.getParameter("txtFN"));
                usuario.setCelular(Long.parseLong(request.getParameter("txtCelular")));
                usuario.setCorreo(request.getParameter("txtCorreo"));
                usuario.setDireccion(request.getParameter("txtDirec"));
                usuario.setColor(request.getParameter("txtColor"));
                usuario.setContrasena(request.getParameter("txtContrasena"));
                usuario.setId_estado_usuario(Integer.parseInt(request.getParameter("txtEstado")));
                //JOptionPane.showMessageDialog(null, usuario.toString());
                if (udao.actualizar_usuario(usuario)) {
                    // Edicion Exitosa
                    request.setAttribute("msg", "editSuccess");
                } else {
                    // Edicion Fallida
                    request.setAttribute("msg", "editFailed");
                }
                if (usuario.getId_tipo_usuario() == 1) {
                    // Cosas para el administrador
                }
                if (usuario.getId_tipo_usuario() == 2) {
                    // Cosas para el cliente
                    listaClientes = udao.listar_usuarios_por_tipo(2);
                    request.setAttribute("listaClientes", listaClientes);
                    request.getRequestDispatcher("paginaClientes.jsp").forward(request, response);
                }
                if (usuario.getId_tipo_usuario() == 3) {
                    // Cosas para el Vendedor
                    listaVendedores = udao.listar_usuarios_por_tipo(3);
                    request.setAttribute("listaVendedores", listaVendedores);
                    request.getRequestDispatcher("paginaVendedores.jsp").forward(request, response);
                }
                break;
            }
            // Casos Clientes
            // --> 1. Mostrar los productos al cliente paara realizar un pedido
            case "listarProductoCliente": {
                pedido = new Pedido();
                pedido.setId_cliente(sesionUsuario.getId_usuario());
                pedido.setFecha_recogida(request.getParameter("txtFR"));
                pedido.setHora_recogida((request.getParameter("txtHR")));
                listaProductos = pdao.listar_productos();
                request.setAttribute("modo", "comprar");
                request.setAttribute("carrito", listaCarrito.size());
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("listarProductoCliente.jsp").forward(request, response);
                break;
            }
            // --> 4. El cliente confirma los productos
            case "confirmarProductos": {
                totalPagar = 0;
                String jsonData = request.getParameter("data");
                listaCarrito = convertirJSONaLista(jsonData);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar += listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("datosFacturacion.jsp").forward(request, response);
                break;
            }
            // --> 5. El cliente finaliza el pedido
            case "finalizarPedido": {
                pedido.setId_estado_pedido(1);
                pedido.setNombre_recoge(request.getParameter("txtPersonaR"));
                pedido.setTelefono_contacto(Long.parseLong(request.getParameter("txtTelCont")));
                pedido.setTotal(totalPagar);
                int id_pedido = pedao.registro_pedido(pedido);
                pedido.setId_pedido(id_pedido);
                if (id_pedido != -1) {
                    request.setAttribute("msg", "RegistroSuccess");
                    dvdao.registrar_detallePedido(listaCarrito, id_pedido);
                } else {
                    request.setAttribute("msg", "RegisterError");
                }
                listaPedidosCliente = pedao.listar_pedidos_por_cliente(sesionUsuario.getId_usuario());
                request.setAttribute("listaPedidosCliente", listaPedidosCliente);
                request.getRequestDispatcher("historialCliente.jsp").forward(request, response);
                break;
            }
            // Casos Vendedores
            // --> 1. Aceptar un pedido como Vendedor
            case "Aceptar": {
                int idPedido = Integer.parseInt(request.getParameter("idpedido"));
                pedao.aceptar_pedido(idPedido);
                venta = new Venta(idPedido, sesionUsuario.getId_usuario(), Integer.parseInt(nserie.numeroSerie()), fechaActual());
                vdao.registro_venta(venta);
                listaPedidosEspera = pedao.listar_pedidos_espera();
                listaProductosDetalle = dvdao.listar_detalle_pedido_todos();
                request.setAttribute("listaProductosDetalle", listaProductosDetalle);
                request.setAttribute("listaPedidosEspera", listaPedidosEspera);
                request.setAttribute("msg", "ventAcept");
                request.getRequestDispatcher("ventaSolicitadas.jsp").forward(request, response);
                break;
            }
            // --> 2. Registrar un cliente como Vendedor
            case "Guardar Cliente": {
                String fecha_N = request.getParameter("txtFNC");
                String color = request.getParameter("txtColorC");
                long cedula = Long.parseLong(request.getParameter("txtCedulaC"));
                String nombre = request.getParameter("txtNombreC"); //Trae el input que tiene como name txtNombre
                String apellido = request.getParameter("txtApellidoC");
                long celular = Long.parseLong(request.getParameter("txtCelularC"));
                String correo = request.getParameter("txtCorreoC");
                String direccion = request.getParameter("txtDirecC");
                String contrasena = UUID.randomUUID().toString().substring(0, 6);
                usuario = new Usuario(cedula, nombre, apellido, fecha_N, celular, correo, direccion, color, contrasena, 1, 2);
                if (udao.verificar_correo_usuario(usuario)) {
                    // El correo ya esta registrado
                    request.setAttribute("msg", "correoExist");
                } else {
                    if (udao.verificar_cedula_usuario(usuario)) {
                        // La cedula ya esta registrada
                        request.setAttribute("msg", "RegisterErrorUserBD");
                    } else {
                        if (udao.registro_usuario(usuario)) {
                            // Registro Exitoso
                            request.setAttribute("msg", "registerSuccess");
                        } else {
                            // Algun error al registrar
                            request.setAttribute("msg", "registerError");
                        }
                    }
                }
                request.setAttribute("u", usuario);
                request.getRequestDispatcher("formularioCliente.jsp").forward(request, response);
                break;
            }
            // --> 2. Finalizar la venta como Vendedor
            case "Generar Venta": {
                if ("".equals(cantidadTotalAPagar) || clienteBuscado == null || "".equals(clienteBuscado.getNombre())) {
                    request.setAttribute("amensaje", "errorVentaSinCamp");
                } else {
                    pedido = new Pedido(clienteBuscado.getId_usuario(), cantidadTotalAPagar, fechaActual(), fechaActual(), clienteBuscado.getNombre(), clienteBuscado.getCelular(), 4);
                    int idPedido = pedao.registro_pedido(pedido);
                    if (idPedido != -1) {
                        request.setAttribute("amensaje", "ventaRCE");
                        listaCarrito = carrito.TransformarVentaTabla(listaVentasTabla);
                        dvdao.registrar_detallePedido(listaCarrito, idPedido);
                        venta = new Venta(idPedido, sesionUsuario.getId_usuario(), numeroSerie, fechaActual());
                        vdao.registro_venta(venta);
                        clienteBuscado = null;
                        cantidadTotalAPagar = 0;
                        itemVenta = 0;
                        numeroSerie = Long.parseLong(nserie.numeroSerie());
                        listaVentasTabla = new ArrayList<>();
                    } else {
                        request.setAttribute("amensaje", "ventaCEI");
                    }
                }
                if (clienteBuscado != null) {
                    // El cliente existe
                    request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                    request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                }
                request.setAttribute("totPagar", cantidadTotalAPagar);
                request.setAttribute("listaProductosTabla", listaVentasTabla);
                request.setAttribute("numeroSer", numeroSerie);
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // --> 3. Cancelar la venta como Vendedor
            case "Cancelar Venta": {
                clienteBuscado = null;
                cantidadTotalAPagar = 0;
                itemVenta = 0;
                numeroSerie = Long.parseLong(nserie.numeroSerie());
                listaVentasTabla = new ArrayList<>();
                request.setAttribute("totPagar", cantidadTotalAPagar);
                request.setAttribute("numeroSer", numeroSerie);
                request.setAttribute("amensaje", "ventaCEI");
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // --> 4. Buscar un cliente para la venta
            case "Buscar": {
                long cedula = Long.parseLong(request.getParameter("txtCedulaC"));
                clienteBuscado = udao.buscar_cliente_por_cedula(cedula);
                if (clienteBuscado != null) {
                    // El cliente existe
                    request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                    request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                } else {
                    // El cliente no existe
                    request.setAttribute("amensaje", "clientNoexist");
                }
                request.setAttribute("totPagar", cantidadTotalAPagar);
                request.setAttribute("listaProductosTabla", listaVentasTabla);
                request.setAttribute("numeroSer", numeroSerie);
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // --> 5. Registrar el producto para la venta
            case "Buscar Producto": {
                String codigoProducto = request.getParameter("codigoProducto");
                productoBuscado = pdao.buscar_producto_por_codigo(codigoProducto);
                if (productoBuscado != null) {
                    if (clienteBuscado != null) {
                        request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                        request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                    }
                    request.setAttribute("nombreProductoV", productoBuscado.getNombre());
                    request.setAttribute("precioProductoV", productoBuscado.getPrecio_venta());
                    request.setAttribute("stockProductoV", productoBuscado.getStock());
                    request.setAttribute("codProductoV", productoBuscado.getCodigo());
                } else {
                    if (clienteBuscado != null) {
                        request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                        request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                    }
                    request.setAttribute("amensaje", "productNoexist");
                }
                request.setAttribute("numeroSer", numeroSerie);
                request.setAttribute("totPagar", cantidadTotalAPagar);
                request.setAttribute("listaProductosTabla", listaVentasTabla);
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // --> 6. Agregar producto a la venta
            case "Agregar Producto": {
                VentaTabla vt = new VentaTabla();
                itemVenta += 1;
                int cantidad = Integer.parseInt(request.getParameter("nTotal"));
                vt.setNumeroVenta(itemVenta);
                vt.setCodigo(productoBuscado.getCodigo());
                vt.setNombre(productoBuscado.getNombre());
                vt.setPrecio(productoBuscado.getPrecio_venta());
                vt.setCantidad(cantidad);
                vt.setId_producto(productoBuscado.getId_producto());
                vt.setSubtotal(productoBuscado.getPrecio_venta() * cantidad);
                cantidadTotalAPagar += vt.getSubtotal();
                listaVentasTabla.add(vt);
                if (clienteBuscado != null) {
                    request.setAttribute("cedulaCliente", clienteBuscado.getCedula());
                    request.setAttribute("nombreCliente", clienteBuscado.getNombre());
                }
                request.setAttribute("totPagar", cantidadTotalAPagar);
                request.setAttribute("numeroSer", numeroSerie);
                request.setAttribute("listaProductosTabla", listaVentasTabla);
                request.getRequestDispatcher("formularioVenta.jsp").forward(request, response);
                break;
            }
            // Casos Administrador
            case "a": {
                break;
            }
            // Casos pedidos
            // --> 1. Mostrar el detalle de una pedido al cliente
            case "detallePedido": {
                String productos = "";
                int idPedido = Integer.parseInt(request.getParameter("idpedido"));
                listaProductosDetalle = dvdao.listar_productos_detalle_venta(idPedido);
                for (int i = 0; i < listaProductosDetalle.size(); i++) {
                    productos += "\\n" + (i + 1) + ") Nombre: " + listaProductosDetalle.get(i).getNombre_producto();
                    productos += "\\n-----> Cantidad: " + listaProductosDetalle.get(i).getCantidad();
                    request.setAttribute("detallePedido", productos);
                }
                request.setAttribute("msg", "productos");
                listaPedidosCliente = pedao.listar_pedidos_por_cliente(sesionUsuario.getId_usuario());
                request.setAttribute("listaPedidosCliente", listaPedidosCliente);
                request.getRequestDispatcher("historialCliente.jsp").forward(request, response);
                break;
            }
            // --> 2. Ir a la pagina editar pedido
            case "pagEditarPedido": {
                pedido.setId_pedido(Integer.parseInt(request.getParameter("idpedido")));
                pedido = pedao.buscar_producto_por_Id(pedido);
                request.setAttribute("btnConfirmar", "editar");
                request.setAttribute("pedido", pedido);
                request.setAttribute("totalPagar", pedido.getTotal());
                request.getRequestDispatcher("datosFacturacion.jsp").forward(request, response);
                break;
            }
            // --> 3. Editar pedido
            case "editarPedido": {
                pedido.setNombre_recoge(request.getParameter("txtPersonaR"));
                pedido.setTelefono_contacto(Long.parseLong(request.getParameter("txtTelCont")));
                pedao.actualizar_pedido(pedido);
                listaPedidosCliente = pedao.listar_pedidos_por_cliente(sesionUsuario.getId_usuario());
                request.setAttribute("msg", "editSucces");
                request.setAttribute("listaPedidosCliente", listaPedidosCliente);
                request.getRequestDispatcher("historialCliente.jsp").forward(request, response);
                break;
            }
            // Casos Ventas
            // --> 1. Mostrar el detalle de una venta al administrador
            case "detalleVenta": {
                String productos = "";
                int idPedido = Integer.parseInt(request.getParameter("idpedido"));
                listaProductosDetalle = dvdao.listar_productos_detalle_venta(idPedido);
                for (int i = 0; i < listaProductosDetalle.size(); i++) {
                    productos += "\\n" + (i + 1) + ") Nombre: " + listaProductosDetalle.get(i).getNombre_producto();
                    productos += "\\n-----> Cantidad: " + listaProductosDetalle.get(i).getCantidad();
                    request.setAttribute("detallePedido", productos);
                }
                request.setAttribute("msg", "detalle");
                listaVentas = vdao.listar_ventas();
                request.setAttribute("listaVentas", listaVentas);
                request.getRequestDispatcher("paginaVentas.jsp").forward(request, response);
                break;
            }
            // Casos por defectos
            // --> 1. Finalizar Sesion de un usuario
            case "cerrarsesion": {
                session.invalidate();
                request.getRequestDispatcher("index.html").forward(request, response);
                break;
            }
            // En caso de errores
            default: {
                session.invalidate();
                request.getRequestDispatcher("error404.html").forward(request, response);
                break;
            }
        }
    }

    private ArrayList<Carrito> convertirJSONaLista(String jsonData) {
        Gson gson = new Gson();
        TypeToken<ArrayList<Carrito>> token = new TypeToken<ArrayList<Carrito>>() {
        };
        ArrayList<Carrito> listaRegistros = gson.fromJson(jsonData, token.getType());
        return listaRegistros;
    }

    @Override
    public String getServletInfo() {
        return "This Servlet is for the system";
    }// </editor-fold>

    private String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha);
    }
}

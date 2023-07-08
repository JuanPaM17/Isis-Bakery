<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Isis Bakery</title>
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="static/css/catalogoProducto.css">

        <!-- custom js file link  -->
        <script src="static/js/catalogoProducto.js" defer></script>

        <!-- CSS only -->
        <script src="https://kit.fontawesome.com/bee51370cb.js" crossorigin="anonymous"></script>
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
            <header>
                <input type="checkbox" id="check">
                <label for="check" class="J">
                    <i class="fas fa-bars" ></i>
                </label>
                <a href="index.jsp" class="logo">
                    <img src="static/imgIndex/logoIsis.png" alt="Logo de la Empresa">
                </a>
                <ul>
                    <li><a href="index.html#pruebaD" class="botonJj">EVENTOS</a></li>
                    <li><a href="index.html#ofertas"  class="botonJj" >INFORMACIÓN</a></li>
                    <li><a href="index.html#productos" class="botonJj">PRODUCTOS</a></li>
                    <li><a href="login.jsp" class="botonJj" >COMPRAR</a></li>
                    <li><a href="index.html#reservas" class="botonJj">CONTACTO</a></li>
                </ul>
            </header>
        </div>
        <div class="container">
            <h3 class="title"> PRODUCTOS </h3>        
            <div class="products-container">               
                <c:forEach var="p" items="${listaProductos}">
                    <div class="product" data-name="${p.getCodigo()}">
                        <img src="${p.getUrlFoto()}" alt="fotoProducto">
                        <h3>${p.getNombre()}</h3>
                        <div class="price">$ ${p.getPrecio_venta()}</div>
                    </div>
                </c:forEach>               
            </div>
        </div>
        <div class="products-preview">         
            <c:forEach var="p" items="${listaProductos}">
                <div class="preview" data-target="${p.getCodigo()}">
                    <i class="fas fa-times"></i>
                    <img src="${p.getUrlFoto()}" alt="">
                    <h3>${p.getNombre()}</h3>
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half-alt"></i>
                        <span>( 250 )</span>
                    </div>
                    <p>${p.getDescripcion()}</p>
                    <div class="price">$ ${p.getPrecio_venta()}</div>
                    <div class="buttons">
                        <a href="login.jsp" class="buy">INICIAR SESIÓN</a>
                    </div>
                </div>           
            </c:forEach>
        </div>
    </body>
</html>


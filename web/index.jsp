<%@ page import="java.util.*" %>
<%@ page import="developerworks.ajax.store.*" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <title> A Cool AJAX Shopping Cart Improved With Json </title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <%-- Loading the two java scripts on loading the page --%> 
        <script type="text/javascript" language="javascript" src="ajax1.js"></script>
        <script type="text/javascript" language="javascript" src="cart.js"></script>
    </head>
    <script>
        <%-- Gets the content of the cart upon page reload --%> 
        addToCart("");
    </script>
    <body>
        <%-- Left side of the web page where the table of items are displayed--%> 
        <div style="float: left; width: 650px">
            <h2>Catalog</h2>
            <table border="2">
                <%-- Columns of the table--%> 
                <thead><th>Name</th><th>Description</th><th>Price</th><th></th><th></th></thead>
                <tbody>
                    <%-- This is a Java Code that will be executed. Creating and iterator for Catalog and iterating through it.--%> 
                    <%
                        for (Iterator<Item> I = new Catalog().getAllItems().iterator(); I.hasNext();) {
                            Item item = I.next();
                    %>
                    <tr><td><%= item.getName()%></td><td><%= item.getDescription()%></td><td><%= item.getFormattedPrice()%></td><td><button onclick="addToCart('<%= item.getCode()%>')">Add to Cart</button></td><td><button onclick="removeFromCart('<%= item.getCode()%>')">Remove From Cart</button></td></tr>
                    <% }%>
                </tbody>
            </table>
                <%-- Right side of the web page where the cart items are displayed--%> 
            <div style="position: absolute; top: 0px; right: 0px; width: 250px">
                <h2>Cart Contents</h2>
                <%-- id tag created for easy identification of part of page to add contents to later--%> 
                <ul id="contents">
                </ul>
                Total cost: <span id="total">$0.00</span>
            </div>
    </body>
</html>
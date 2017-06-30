package developerworks.ajax.servlet;

import developerworks.ajax.store.Cart;
import javax.servlet.http.*;
import java.util.Enumeration;

public class CartServlet extends HttpServlet {

    /**
     * Updates Cart, and outputs XML representation of contents
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
        // reading the contents of the header
        Enumeration headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = (String) headers.nextElement();
            System.out.println(header + ": " + req.getHeader(header));
        }
        // getting the attribute cart from the session
        Cart cart = getCartFromSession(req);
        // extracting the action and item values present in the request message
        String action = req.getParameter("action");
        String item = req.getParameter("item");
        // checking if both action and item are sent from the client
        if ((action != null) && (item != null)) {
            // based on the value of action either adding or deleting the item
            if ("add".equals(action)) {
                cart.addItem(item);
            } else if ("remove".equals(action)) {
                cart.removeItems(item);
            }
        }
        // converting the cart object to an Json string that can be sent over to client
        String cartJson = cart.toJson();
        //setting the channel to json return
        res.setContentType("application/json");
        // sending the json string to the client
        res.getWriter().write(cartJson);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
        // Bounce to post, for debugging use
        // Hit this servlet directly from the browser to see XML
        doPost(req, res);
    }

    private Cart getCartFromSession(HttpServletRequest req) {
        // getting the HttpSession from the request
        HttpSession session = req.getSession(true);
        // getting the cart attribute from the session if present or else creating a new one if not present
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        // returning back the cart object to the method call
        return cart;
    }
}
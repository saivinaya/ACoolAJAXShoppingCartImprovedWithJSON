// Timestamp of cart that page was last updated with
var lastCartUpdate = 0;

/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function addToCart(itemCode) {
//creating a new XMLHttp Request
    var req = newXMLHttpRequest();
// Assigning statechange Handler
    req.onreadystatechange = getReadyStateHandler(req, updateCart);

    req.open("POST", "cart.do", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //sending the request to the server
    req.send("action=add&item=" + itemCode);
}

function removeFromCart(itemCode) {
//creating a new XMLHttp Request
    var req = newXMLHttpRequest();
// Assigning statechange Handler
    req.onreadystatechange = getReadyStateHandler(req, updateCart);

    req.open("POST", "cart.do", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //sending the request to the server
    req.send("action=remove&item=" + itemCode);
}

/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
function updateCart(cartJSON) {
    // Use library to parse the string back from server
    var myJsonObj = JSON.parse(cartJSON);
    // Find the value of the cartGenerated identifier
    var generated = myJsonObj.cart.Generated;
    //updating the display only if the timestamp is new
    if (generated > lastCartUpdate) {
        // assigning new timestamp to lastCartUpdate 
        lastCartUpdate = generated;
        //getting the part of the page having ID as "contents" which was defined in index.jsp previously
        var contents = document.getElementById("contents");
        // making it null, clearing the part of the page
        contents.innerHTML = "";

        // iterating through the items in the cart one by one and adding them to the web page
        var items = myJsonObj.cart.cartItems;
        for (var I = 0; I < items.length; I++) {

            var item = items[I];
            var name = item.name;
            var quantity = item.quantity;

            var listItem = document.createElement("li");
            // adding the items and quatity to the web page
            listItem.appendChild(document.createTextNode(name + " x " + quantity));
            contents.appendChild(listItem);
        }
        // adding the total value to the part of the web page having ID as "total"
        document.getElementById("total").innerHTML = myJsonObj.cart.Total;
    }
}
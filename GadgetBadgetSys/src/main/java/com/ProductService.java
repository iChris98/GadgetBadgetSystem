package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Products")
public class ProductService {
		
	Product productobj = new Product();

	@POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertProduct(String productData) {

		ProductBean pb = new ProductBean(productData);

        String output = productObj.insertProduct(pb);
        return output;
        
	}       
	
	@GET
  	@Path("/")
  	@Produces(MediaType.TEXT_HTML)
  	public String readProductString() {
  		return productObj.readProducts();
  	}
	
	
	@PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateProduct(String productData) {

		ProductBean pb = new ProductBean(productData);

        String output = produtObj.updateProduct(pb);
        return output;

    }
	
	 @DELETE
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String deleteProduct(String productData) {

	  
			JsonObject productObj = new JsonParser().parse(productData).getAsJsonObject();

		    String userid = productObj.get("userID").getAsString();
	        String output = productObj.deleteProducts(userid);

	        return output;

	    }
}

        
        
        



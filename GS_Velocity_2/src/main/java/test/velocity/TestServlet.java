package test.velocity;

import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.velocity.tools.view.VelocityViewServlet;

import test.bean.Product;

/**
 * Created by IntelliJ IDEA.
 * User: GGobozov
 * Date: 02.09.2009
 * Time: 14:19:23
 * To change this template use File | Settings | File Templates.
 */
public class TestServlet extends VelocityViewServlet {

    private Collection products = new ArrayList();

    @Override
    public void init() throws ServletException {
        products.add(new Product("Widget", 12.99));
        products.add(new Product("Wotsit", 13.99));
        products.add(new Product("Thingy", 11.99));
        super.init();
    }

	@Override
    public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {


        if (request.getParameter("submit") != null) {

            List<String> errors = new ArrayList();
            String name = request.getParameter("name");
            System.out.println("name = " + name);

            try {
                double price = Double.parseDouble(request.getParameter("price"));
                System.out.println("price = " + price);
                products.add(new Product(name, price));
            } catch (NumberFormatException e) {
                System.err.println(e);
                errors.add("Price must be a number");
            }
            context.put("errors", errors);
        }
        context.put("products", products);
        context.put("company", "Gemini Systems");

        Template template = getTemplate("products.vm");
        return template;
    }

}

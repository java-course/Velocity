package test.velocity;

import bean.Product;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import java.io.Writer;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: gb
 * Date: 02.09.2009
 * Time: 1:45:08
 * To change this template use File | Settings | File Templates.
 */
public class Test5 {

    public Test5() throws Exception {
        Velocity.init("src/main/java/velocity.properties");
        // get Template
        Template template = Velocity.getTemplate("Test5.vm");
        // getContext
        Context context = new VelocityContext();

        context.put("products", new ArrayList<Product>(){
            {
                add(new Product("Apple", 10));
                add(new Product("Orange", 12));
                add(new Product("Banana", 11));
            }
        });

        // get Writer
        Writer writer = new StringWriter();
        // merge
        template.merge(context, writer);

        System.out.println(writer.toString());
    }

}

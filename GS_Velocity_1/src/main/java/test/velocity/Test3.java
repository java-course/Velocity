package test.velocity;

import bean.Product;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import java.io.Writer;
import java.io.StringWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gb
 * Date: 02.09.2009
 * Time: 1:25:26
 * To change this template use File | Settings | File Templates.
 */
public class Test3 {

    public Test3() throws Exception{
        //init
        Velocity.init("src/main/java/velocity.properties");
        // get Template
        Template template = Velocity.getTemplate("Test3.vm");
        // getContext
        Context context = new VelocityContext();

        context.put("name", "Vova");
        context.put("age", 21);
        context.put("flag", true);
        context.put("today", new Date());
        context.put("product", new Product("Book", 12.3));

        // get Writer
        Writer writer = new StringWriter();
        // merge
        template.merge(context, writer);

        System.out.println(writer.toString());
    }
}

package test.velocity;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import java.io.Writer;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: gb
 * Date: 02.09.2009
 * Time: 1:27:51
 * To change this template use File | Settings | File Templates.
 */
public class Test4 {

    public Test4() throws Exception{

         Velocity.init("src/main/java/velocity.properties");
        // get Template
        Template template = Velocity.getTemplate("Test4.vm");
        // getContext
        Context context = new VelocityContext();

        context.put("a", 5);
        context.put("b", 6);
        context.put("s1", "Hello");
        context.put("s2", "World");

        // get Writer
        Writer writer = new StringWriter();
        // merge
        template.merge(context, writer);

        System.out.println(writer.toString());

    }
}

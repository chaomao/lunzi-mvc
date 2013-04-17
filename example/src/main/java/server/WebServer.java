package server;

import com.thoughtworks.mvc.MVCDispatcherServlet;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

public class WebServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        Context context = new Context(server, "/");
        context.addServlet(new ServletHolder(new FavoriteIconServlet()), "/favicon.ico");
        context.addServlet(new ServletHolder(new MVCDispatcherServlet()), "/*");
        server.start();
    }
}


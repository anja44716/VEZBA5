import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import static java.lang.System.out;

public class MyServlet extends HttpServlet {
    int count;
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        try{
            File file = new File("C:\\Users\\Anja\\Desktop\\brojacposeta.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String pocetno= bufferedReader.readLine();
            count=Integer.parseInt(pocetno);
            return;
        }
        catch(FileNotFoundException e1){
            
        }
        catch(IOException e2){
            
        }
        catch(NumberFormatException e3){
            
        }
        count=0;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            count++;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Brojac poziva</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Od kad je sveta i veka ovaj servlet je pozvan " +count+ " puta.</h1>");
            out.println("<a href=\"MyServlet\">Klikni ponovo</a>");
            out.println("</body>");
            out.println("</html>");
        }
        finally{
            out.close();
        }
    }
    public void destroy(){
        saveState();
    }
    public void saveState(){
        try{
            File file = new File("C:\\Users\\Anja\\Desktop\\brojacposeta.txt");
            FileWriter filewriter = new FileWriter(file);
            String stanje = Integer.toString(count);
            filewriter.write(stanje, 0, stanje.length());
            filewriter.close();
            return;
        }
        catch(IOException e4){
            
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
}

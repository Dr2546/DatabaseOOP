import java.util.HashMap;
import java.sql.*;  
public class DB
{
    
    char type;
    Connection pat;
    
    public boolean init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.pat=DriverManager.getConnection(  
            "jdbc:mysql://db4free.net:3306/dandogdb","dandog","daniel2546");  
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    public boolean insert(String username,String password,String email){
        try{
            Statement stmt=this.pat.createStatement();  
            String insert = "'"+username+"','"+password+"','member','"+email+"'";
            int rs = stmt.executeUpdate("insert into people(username,password,role,email) values("+insert+")");
            if(rs > 0)
            {
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean delete(String username){
        try{
            Statement stmt = this.pat.createStatement();
            String condition = "username='"+username+"'";
            int rs = stmt.executeUpdate("delete from people where "+condition);
            if(rs > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    public HashMap<String,String> find(String username){
        HashMap<String,String> data = new HashMap<>();
        try{
            Statement stmt = this.pat.createStatement();
            ResultSet rs = stmt.executeQuery("select * from people where username='" + username + "'");
            data.put("username",rs.getString(1));
            data.put("password",rs.getString(2));
            data.put("role",rs.getString(3));
            data.put("email",rs.getString(4));
            return data;
        }
        catch(Exception e){
            return data;
        }
    }



    /*public static boolean login(String username,String password){
        boolean res = false;
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://db4free.net:3306/dandogdb","dandog","daniel2546");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from people where username='" + username + "'");
            if(!rs.next())
            {
                System.out.println("No user found");
            }
            else{
                //System.out.println("t");
                //System.out.println(rs.getString(1)+"  "+rs.getString(2));
                if(!password.equals(rs.getString(2)))
                {
                    System.out.println("Password not match");
                }
                else{
                    System.out.println("Login success");
                    res = true;
                }
            }
            con.close();
            return res;  
            }catch(Exception e){ 
                System.out.println(e);
                return false;
            }
    }*/
}  
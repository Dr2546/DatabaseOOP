import java.sql.*;
import java.io.*;
import java.util.HashMap;  

public class DB
{
    
    char type;
    Connection req;
    
    public boolean init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.req=DriverManager.getConnection(  
            "jdbc:mysql://db4free.net:3306/dandogdb","dandog","daniel2546");  
            return true;
        }catch(Exception e)
        {
           // System.out.println(e);
            return false;
        }
    }

    public boolean insert(String username,String password,String email){
        try{
            Statement stmt=this.req.createStatement();  
            String insert = "'"+username+"','"+password+"','1','"+email+"'";
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
            Statement stmt = this.req.createStatement();
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
            Statement stmt = this.req.createStatement();
            ResultSet rs = stmt.executeQuery("select * from people where username='" + username + "'");
            rs.next();
            data.put("username",rs.getString(1));
            data.put("password",rs.getString(2));
            data.put("role",rs.getString(3));
            data.put("email",rs.getString(4));
            return data;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean insertImg(String path,String imgname){
        try{
            File file = new File(path);
            FileInputStream fis=new FileInputStream(file);
            PreparedStatement ps=this.req.prepareStatement("insert into img (name,image) values(?,?)");
            ps.setString(1,imgname);
            ps.setBinaryStream(2,fis,(int)file.length());
            ps.executeUpdate();
 
            ps.close();
            fis.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean getImg(String path,String imgname,String imgtype){
        try{
            File file;
            if(path.equals("none"))
                {
                    file = new File("System.getProperty(\"user.home\")" + File.separator + "\"testimg\"");
                    file.mkdirs();
                }
            else 
                file = new File(path);
            FileOutputStream fos=new FileOutputStream(new File(file, imgname+"."+imgtype));
            byte b[];
            Blob blob;
            String sqlcommand = "select * from img where name=\"" + imgname + "\"";
            PreparedStatement ps=this.req.prepareStatement(sqlcommand);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                blob=rs.getBlob("image");
                b=blob.getBytes(1,(int)blob.length());
                fos.write(b);
            }
            ps.close();
            fos.close();
            

            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Connection getCon()
    {
        return this.req;
    }

    public DB() {
        this('u');
    }

    public DB(char type) {
        this.type = type;
    }

}  
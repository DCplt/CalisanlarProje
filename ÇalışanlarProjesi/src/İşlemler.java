
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class İşlemler {
    private Connection con = null;
    private Statement statement = null;
    
    private PreparedStatement PreparedStatement = null;
    
    public İşlemler(){
        
        String url = "jdbc:mysql://"+Database.host+":"+Database.port+"/"+Database.db+"?useUnicode=true&characterEncoding=utf8";
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (Exception e) {
            
            System.out.println("hata");
        }
        try {
            
            con = DriverManager.getConnection(url,Database.kullanıcı_Adı,Database.parola);
            System.out.println("Bağlantı başarılı");
            
        } catch (SQLException ex) {
            
            System.out.println("Hata");
        }
    }
    public boolean adminGirişiKontrol(String kullanıcıAdı,String Parola){
        String sorgu = "Select * From adminler Where username = ? and password = ?";
        
        try {
            PreparedStatement = con.prepareStatement(sorgu);
            
            PreparedStatement.setString(1, kullanıcıAdı);
            PreparedStatement.setString(2, Parola);
            
            ResultSet rs = PreparedStatement.executeQuery();
            
            return rs.next();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(İşlemler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public ArrayList<Calışanlar> ÇalışanlarıGetir(){
        ArrayList <Calışanlar> cıktı = new ArrayList<Calışanlar>();
        String sorgu = "Select * From calisanlar";
        try {
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery(sorgu);
            while (rs.next()) {                
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String departman = rs.getString("departman");
                String maas = rs.getString("maas");
                
                cıktı.add(new Calışanlar(id,ad,soyad,departman,maas)); 
            }
            return cıktı;
            
        } catch (SQLException ex) {
            Logger.getLogger(İşlemler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void çalışanGüncelle(int id,String ad,String soyad,String departman,String maas){
        String sorgu = "Update calisanlar set ad=?,soyad=?,departman=?,maas=? Where id=?";
        
        try {
            PreparedStatement = con.prepareStatement(sorgu);
            
            PreparedStatement.setString(1, ad);
            PreparedStatement.setString(2, soyad);
            PreparedStatement.setString(3, departman);
            PreparedStatement.setString(4, maas);
            PreparedStatement.setInt(5, id);
            
            PreparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(İşlemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void çalışanSilme(int id){
        String sorgu = "Delete From calisanlar Where id=?";
        
        try {
            PreparedStatement = con.prepareStatement(sorgu);
            PreparedStatement.setInt(1, id);
            
            PreparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(İşlemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void ÇalışanEkle(String ad,String soyad,String departman, String maas){
        String Sorgu = "Insert into calisanlar (ad,soyad,departman,maas) Values (?,?,?,?)";
        
        try {
            PreparedStatement = con.prepareStatement(Sorgu);
            
            PreparedStatement.setString(1, ad);
            PreparedStatement.setString(2, soyad);
            PreparedStatement.setString(3, departman);
            PreparedStatement.setString(4, maas);
            
            PreparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(İşlemler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        İşlemler işlemler = new İşlemler();
    }
  }


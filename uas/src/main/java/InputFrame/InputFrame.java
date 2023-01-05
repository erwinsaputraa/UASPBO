package InputFrame;
import koneksi.bumi;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InputFrame extends JFrame{
    private JPanel MainPanel;
    private JButton saveButton;
    private JButton batalButton;
    private JTextField ID;
    private JTextField Nama1;
    private JTextField NOhp;
    private JTextField Noimei;
    private JTextField Typhp;
    private JTextField paket1;
    private int id;

    public InputFrame(){
        saveButton.addActionListener(e ->{
            String nama = Nama1.getText();
            String no_hp = NOhp.getText();
            String no_imei = Noimei.getText();
            String type_hp = Typhp.getText();
            String paket = paket1.getText();
            Connection c = bumi.getConnection();
            PreparedStatement ps;
            try {
                if (id == 0){
                    String insertSQL = "INSERT INTO dataimei VALUES (NULL, ?, ?, ?, ?, ?)";
                    ps = c.prepareStatement(insertSQL);
                    ps.setString(1,nama);
                    ps.setString(2,no_hp);
                    ps.setString(3,no_imei);
                    ps.setString(4,type_hp);
                    ps.setString(5,paket);
                    ps.executeUpdate();
                    dispose();
                }else {
                    String updateSQL = "UPDATE dataimei SET NAMA = ?,"+"no_hp = ?,"+"no_imei = ?,"+"type_hp = ?,"+"Paket = ?";
                    ps = c.prepareStatement(updateSQL);
                    ps.setString(1,nama);
                    ps.setString(2,no_hp);
                    ps.setString(3,no_imei);
                    ps.setString(4,type_hp);
                    ps.setString(5,paket);
                    ps.setInt(6, id);
                    ps.executeUpdate();
                    dispose();
                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });
        init();
    }
    public void init(){
        setContentPane(MainPanel);
        setTitle("Data Order");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public  void setID(int id){ this.id = id;}

    public void isiKomponen(){
        Connection c = bumi.getConnection();
        String findSQL = "SELECT * FROM unclockimei WHERE ID = ?";
        PreparedStatement ps = null;
        try{
            ps = c.prepareStatement(findSQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                ID.setText(String.valueOf(rs.getInt("id")));
                Nama1.setText(String.valueOf(rs.getString("nama")));
                NOhp.setText(String.valueOf(rs.getString("no_hp")));
                Noimei.setText(String.valueOf(rs.getString("no_imei")));
                Typhp.setText(String.valueOf(rs.getString("type_hp")));
                paket1.setText(String.valueOf(rs.getString("paket")));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

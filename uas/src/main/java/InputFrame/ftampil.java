package InputFrame;

import koneksi.bumi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class ftampil extends JFrame{
    private JTable viewTabel;
    private JButton createButton;
    private JButton keluarButton;
    private JButton deleteButton;
    private JButton readButton;
    private JButton updateButton;
    private JPanel mainPanel;

    public ftampil(){
        createButton.addActionListener(e ->{
            InputFrame If = new InputFrame();
            If.setVisible(true);
        });
         deleteButton.addActionListener(e ->{
             int baristerpilih = viewTabel.getSelectedRow();
             if (baristerpilih < 0){
                 JOptionPane.showMessageDialog(null,"pilih data dulu");
                 return;
             }
             int pilihan = JOptionPane.showConfirmDialog(null,"yakin mau hapus?","konfirmasi hapus",JOptionPane.YES_NO_OPTION);
             if (pilihan == 0){
                 TableModel tm = viewTabel.getModel();
                 int id = Integer.parseInt(tm.getValueAt(baristerpilih,0).toString());
                 Connection c = bumi.getConnection();
                 String deleteSQL = "DELETE FROM kota WHERE id = ?";
                 try{
                     PreparedStatement ps = c.prepareStatement(deleteSQL);
                     ps.setInt(1, id);
                     ps.executeUpdate();
                 }catch (SQLException ex){
                     throw new RuntimeException(ex);
                 }
             }
         });
         updateButton.addActionListener(e ->{
             int barisTerpilih = viewTabel.getSelectedRow();
             if (barisTerpilih < 0){
                 JOptionPane.showMessageDialog(null,"pilih Data Dulu");
                 return;
             }
             TableModel tm = viewTabel.getModel();
             int id = Integer.parseInt(tm.getValueAt(barisTerpilih,0).toString());
             InputFrame inputFrame = new InputFrame();
             inputFrame.setID(id);
             inputFrame.isiKomponen();
             inputFrame.setVisible(true);

         });
         readButton.addActionListener(e ->{
             isiTable();
         });
         isiTable();
         init();

    }

    public void init(){
        setContentPane(mainPanel);
        setTitle("Data Penjual");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void isiTable(){
        Connection c = bumi.getConnection();
        String selectSQL = "SELECT * FROM unclockimei";
        try {
            Statement s = c.createStatement();
            ResultSet rs =s.executeQuery(selectSQL);
            String header[] = {"id","nama","no_hp","no_imei","type_hp","paket"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            viewTabel.setModel(dtm);
            viewTabel.getColumnModel().getColumn(0).setMaxWidth(3);
            Object[] row = new Object[3];
            while (rs.next()){
                row [0] = rs.getInt("id");
                row [1] = rs.getInt("nama");
                row [2] = rs.getInt("no_hp");
                row [3] = rs.getInt("no_imei");
                row [4] = rs.getInt("type_hp");
                row [5] = rs.getInt("Paket");
                dtm.addRow(row);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}


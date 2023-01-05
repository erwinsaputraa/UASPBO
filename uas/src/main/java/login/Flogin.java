package login;

import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.swing.*;
import java.util.prefs.Preferences;

public class Flogin extends JFrame{

    private JPanel Flogin;
    private JButton loginButton;
    private JButton keluarButton;
    private JTextField textField1;
    private JTextField textField2;

    public Flogin(){
        setContentPane(Flogin);
        loginButton.addActionListener(e -> {
            if (textField1.getText().equals("admin")) {
                if (new String(textField2.getText()).equals("admin")){
                    Preferences pref = Preferences.userRoot().node(Main.class.getName());
                    pref.put("UserID","1");
                    Flogin FP = new Flogin();
                    dispose();
                }
            }
        });
    }
}

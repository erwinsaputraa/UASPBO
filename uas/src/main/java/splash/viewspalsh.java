package splash;


import login.Flogin;
import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

public class viewspalsh extends JFrame{
    private JPanel MainPanel;

    public viewspalsh(){
        setContentPane(MainPanel);
        setSize(640,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Preferences prof = Preferences.userRoot().node(Main.class.getName());
        String userID = prof.get("userID","");
        System.out.println(userID);

        try {
            TimeUnit.SECONDS.sleep(2);
            if (userID.equals("")) {
                setVisible(false);
                Flogin lf = new Flogin();
                lf.setVisible(true);
                lf.setSize(320, 140);

            } else {
                setVisible(false);
                Flogin lf = new Flogin();
                lf.setVisible(true);
                lf.setSize(320, 120);
            }
        } catch (InterruptedException exception ){
                throw new RuntimeException();
            }


        }
    }

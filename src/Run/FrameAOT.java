package Run;

import Effect.CacheDataLoader;
import java.io.IOException;
import javax.swing.JFrame;

public class FrameAOT extends JFrame {

    public static PanelAOT panelAOT;

    public FrameAOT() {
        panelAOT = new PanelAOT();
        add(panelAOT);
        creatAndShow();
        addKeyListener(panelAOT);
        addMouseListener(panelAOT);
        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex) {

        }
    }

    public void creatAndShow() {
        setTitle("Attack on T");
        setSize(1200, 800);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(this);
        setVisible(true);
    }

    public void startGame() {
        panelAOT.startGame();
    }

    public static void main(String[] args) {
        FrameAOT frameAOT = new FrameAOT();
        frameAOT.startGame();
    }
}

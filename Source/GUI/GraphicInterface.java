import Logic.InitMap;
import org.ini4j.Ini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class GraphicInterface {
    JFrame programFrame;
    InitializeForm initializeForm;

    Drawer drawer;

    Map map;


    /**
     * Konstruktor dla obiektu. Od razu uzupelnia JFrame o pierwsy panel
     */
    public GraphicInterface() {
        this.programFrame = new JFrame();
        this.programFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.programFrame.setMinimumSize(new Dimension(300, 300));
        this.initializeForm = new InitializeForm();
        this.initializeForm.initializePanel.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {

            }

            @Override
            public void componentRemoved(ContainerEvent e) {

                //initializeForm = null; // chyba powinno to byc, ale nie dam uciac sobie glowy. Najwyzej bedzie smiec w pamieci
            }
        }); // listener do usuwawania panelu uruchamiającego. Spaghetti code, ale jak zrobic to lepiej?
        this.initializeForm.generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                initializeForm.xSize = (int) initializeForm.sizeXSpinner.getValue();
                initializeForm.ySize = (int) initializeForm.sizeYSpinner.getValue();
                if (initializeForm.xSize < initializeForm.minimalSizeX || initializeForm.ySize < initializeForm.minimalSizeY)
                    JOptionPane.showMessageDialog(initializeForm.initializePanel, "Canvas too small");
                else if (initializeForm.xSize > initializeForm.minimalSizeX && initializeForm.ySize > initializeForm.minimalSizeY) {
                    programFrame.remove(initializeForm.initializePanel);
                    map = new Map(initializeForm.xSize, initializeForm.ySize);
                    drawer = new Drawer();
                    drawer.setMap(map);
                    programFrame.repaint();
                    showPaintPanel();
                    InitMap.Mapa = new int [initializeForm.xSize][initializeForm.ySize];
                    InitMap.x=initializeForm.xSize;
                    InitMap.y=initializeForm.ySize;

                } else
                    JOptionPane.showMessageDialog(programFrame, "Wut?");
            }
        }); //przycisniecie przycisku. Spaghetti code, ale jak zrobic to lepiej?
        this.programFrame.add(initializeForm.initializePanel);
        this.programFrame.pack();
        this.programFrame.setVisible(true);

    }

    public void showPaintPanel() {
        this.programFrame.setVisible(false);
        this.programFrame.remove(this.initializeForm.initializePanel);
        this.programFrame.setSize(300, 300);
        this.programFrame.add(this.drawer.mainPanel);
        this.programFrame.pack();
        this.programFrame.repaint();
        this.programFrame.setVisible(true);
    }
}



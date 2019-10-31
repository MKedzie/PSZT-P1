import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class GraphicInterface {
    JFrame programFrame;
    InitializeForm initializeForm;
    Map map;

    public GraphicInterface() {
        this.programFrame = new JFrame();
        this.programFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initializeForm = new InitializeForm();
        this.initializeForm.initializePanel.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {

            }

            @Override
            public void componentRemoved(ContainerEvent e) {

                map = new Map(initializeForm.xSize, initializeForm.ySize);


            }
        });
        this.initializeForm.generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                initializeForm.xSize = (int) initializeForm.sizeXSpinner.getValue();
                initializeForm.ySize = (int) initializeForm.sizeYSpinner.getValue();
                if (initializeForm.xSize < initializeForm.minimalSizeX || initializeForm.ySize < initializeForm.minimalSizeY)
                    JOptionPane.showMessageDialog(initializeForm.initializePanel, "Canvas too small");
                if (initializeForm.xSize > initializeForm.minimalSizeX && initializeForm.ySize > initializeForm.minimalSizeY)
                    programFrame.remove(initializeForm.initializePanel);
                else
                    JOptionPane.showMessageDialog(programFrame, "Wut?");
            }
        });
        this.programFrame.add(initializeForm.initializePanel);
        this.programFrame.pack();
        this.programFrame.setVisible(true);

    }

}



import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class InitializeForm {
    final static int minimalSizeX = 1;
    final static int minimalSizeY = 1;
    protected JSpinner sizeYSpinner;
    protected JSpinner sizeXSpinner;
    protected JButton generateButton;
    protected JSlider wallSlider;
    protected JTextArea buildTheWallOrangeTextArea;
    protected JPanel initializePanel;
    protected JTextPane importPane;
    protected JButton importButton;
    protected JTextField importTextField;
    int xSize;
    int ySize;


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        initializePanel = new JPanel();
        initializePanel.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        sizeYSpinner = new JSpinner();
        initializePanel.add(sizeYSpinner, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sizeXSpinner = new JSpinner();
        initializePanel.add(sizeXSpinner, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Size X");
        initializePanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 36), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Size Y");
        initializePanel.add(label2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 36), null, 0, false));
        wallSlider = new JSlider();
        wallSlider.setInverted(true);
        wallSlider.setMaximum(25);
        wallSlider.setPaintLabels(false);
        wallSlider.setVisible(false);
        initializePanel.add(wallSlider, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buildTheWallOrangeTextArea = new JTextArea();
        buildTheWallOrangeTextArea.setEditable(false);
        buildTheWallOrangeTextArea.setEnabled(false);
        buildTheWallOrangeTextArea.setText("Build the Wall\nOrange man bad");
        buildTheWallOrangeTextArea.setVisible(false);
        initializePanel.add(buildTheWallOrangeTextArea, new GridConstraints(4, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Wall slider");
        label3.setVisible(false);
        initializePanel.add(label3, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateButton = new JButton();
        generateButton.setText("Generate");
        initializePanel.add(generateButton, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(81, 199), null, 0, false));
        importButton = new JButton();
        importButton.setText("Button");
        initializePanel.add(importButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        importTextField = new JTextField();
        initializePanel.add(importTextField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return initializePanel;
    }


}

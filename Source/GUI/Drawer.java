import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class Drawer {
    protected JPanel mainPanel;
    protected JPanel drawingPanel;
    protected JPanel upPanel;
    protected JPanel drawerSettingsPanel;
    protected JPanel settingPanel;
    protected JPanel leftPanel;
    protected JRadioButton freeButton;
    protected JRadioButton wallButton;
    protected JRadioButton enterButton;
    protected JRadioButton exitButton;
    ;
    protected Color freeColor;
    protected Color wallColor;
    protected Color enterColor;
    protected Color exitColor;
    protected Map map;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    protected void initColors() {
        this.enterColor = Color.GREEN;
        this.exitColor = Color.BLUE;
        this.freeColor = Color.WHITE;
        this.wallColor = Color.BLACK;
    }

    public void setMap(Map map) { // call from other place
        this.map = map;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        upPanel = new JPanel();
        upPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(upPanel, BorderLayout.NORTH);
        drawingPanel = new JPanel();
        drawingPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(drawingPanel, BorderLayout.CENTER);
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(leftPanel, BorderLayout.EAST);
        drawerSettingsPanel = new JPanel();
        drawerSettingsPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(drawerSettingsPanel, BorderLayout.WEST);
        freeButton = new JRadioButton();
        freeButton.setText("Free Tile");
        drawerSettingsPanel.add(freeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wallButton = new JRadioButton();
        wallButton.setText("Wall Tile");
        drawerSettingsPanel.add(wallButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterButton = new JRadioButton();
        enterButton.setText("Enter Tile");
        drawerSettingsPanel.add(enterButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JRadioButton();
        exitButton.setText("Exit Tile");
        drawerSettingsPanel.add(exitButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(settingPanel, BorderLayout.SOUTH);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(freeButton);
        buttonGroup.add(wallButton);
        buttonGroup.add(enterButton);
        buttonGroup.add(exitButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }


}

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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
    protected JComboBox heuristicBox;
    protected JButton executeButton;
    protected JTextField resultField;
    protected JTextField seedField;
    protected JButton importButton;
    protected JButton exportButton;
    protected JSpinner resizeYSpinner;
    protected JSpinner resizeXSpinner;
    protected JButton resizeButton;
    protected JLabel drawingLabel;

    protected Color freeColor;
    protected Color wallColor;
    protected Color enterColor;
    protected Color exitColor;
    protected Color wayColor;
    protected Map map;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public void setMap(Map map) { // call from other place
        this.map = map;
    }

    public Drawer() {
        initColors();
        this.drawingPanel.setBackground(Color.BLACK);
        drawingPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                drawingLabel.setIcon(new ImageIcon(recalculateImage()));

            }
        });

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point point = e.getPoint();
                int sizeX = drawingPanel.getWidth();
                int sizeY = drawingPanel.getHeight();
                int horizontalLinesSpaceBetween = sizeY / map.getMapSizeY();
                int verticalLinesSpaceBetween = sizeX / map.getMapSizeX();
                int row = point.x / verticalLinesSpaceBetween;
                int column = point.y / horizontalLinesSpaceBetween;
                map.setTile(row, column, getCurrentSelectedFieldType());
                drawingLabel.setIcon(new ImageIcon(recalculateImage()));
            }
        });

        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Point point = e.getPoint();

                int sizeX = drawingPanel.getWidth();
                int sizeY = drawingPanel.getHeight();
                int horizontalLinesSpaceBetween = sizeY / map.getMapSizeY();
                int verticalLinesSpaceBetween = sizeX / map.getMapSizeX();
                int row = point.x / verticalLinesSpaceBetween;
                int column = point.y / horizontalLinesSpaceBetween;
                map.setTile(row, column, getCurrentSelectedFieldType());
                drawingLabel.setIcon(new ImageIcon(recalculateImage()));
            }
        });
    }

    protected void initColors() {
        this.enterColor = Color.GREEN;
        this.exitColor = Color.RED;
        this.freeColor = Color.WHITE;
        this.wallColor = Color.BLACK;
        this.wayColor = Color.BLUE;
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
        mainPanel.setMinimumSize(new Dimension(50, 50));
        mainPanel.setPreferredSize(new Dimension(51, 51));
        upPanel = new JPanel();
        upPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(upPanel, BorderLayout.NORTH);
        seedField = new JTextField();
        upPanel.add(seedField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        importButton = new JButton();
        importButton.setText("Import");
        upPanel.add(importButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exportButton = new JButton();
        exportButton.setText("Export");
        upPanel.add(exportButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(leftPanel, BorderLayout.EAST);
        resizeYSpinner = new JSpinner();
        leftPanel.add(resizeYSpinner, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        leftPanel.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Size vertical");
        leftPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Size horizontal");
        leftPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resizeXSpinner = new JSpinner();
        leftPanel.add(resizeXSpinner, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resizeButton = new JButton();
        resizeButton.setText("Resize");
        leftPanel.add(resizeButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        drawerSettingsPanel = new JPanel();
        drawerSettingsPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(drawerSettingsPanel, BorderLayout.WEST);
        freeButton = new JRadioButton();
        freeButton.setSelected(true);
        freeButton.setText("Free Tile");
        drawerSettingsPanel.add(freeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wallButton = new JRadioButton();
        wallButton.setSelected(false);
        wallButton.setText("Wall Tile");
        drawerSettingsPanel.add(wallButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterButton = new JRadioButton();
        enterButton.setText("Enter Tile");
        drawerSettingsPanel.add(enterButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JRadioButton();
        exitButton.setText("Exit Tile");
        drawerSettingsPanel.add(exitButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(settingPanel, BorderLayout.SOUTH);
        heuristicBox = new JComboBox();
        settingPanel.add(heuristicBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        executeButton = new JButton();
        executeButton.setText("Find route");
        settingPanel.add(executeButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultField = new JTextField();
        resultField.setText("Result");
        settingPanel.add(resultField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        drawingPanel = new JPanel();
        drawingPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        drawingPanel.setMinimumSize(new Dimension(24, 24));
        mainPanel.add(drawingPanel, BorderLayout.CENTER);
        drawingLabel = new JLabel();
        drawingLabel.setText("Label");
        drawingPanel.add(drawingLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    private BufferedImage recalculateImage() {
        int sizeX = this.drawingPanel.getWidth();
        int sizeY = this.drawingPanel.getHeight();
        int mapSizeX = this.map.getMapSizeX();
        int mapSizeY = this.map.getMapSizeY();
        BufferedImage imageToDraw = UIUtil.createImage(sizeX, sizeY, BufferedImage.TYPE_3BYTE_BGR)/*new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_3BYTE_BGR)*/;
        Graphics graphics = imageToDraw.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, sizeX, sizeY);
        graphics.setColor(Color.BLACK);
        // zaczne od linii poziomych - y const i x1 x2 min max
        // tyle tych linii co sizey,
        int horizontalLinesSpaceBetween = sizeY / mapSizeY;
        for (int i = 0; i <= mapSizeY; i++)
            graphics.drawLine(0, horizontalLinesSpaceBetween * i, sizeX, horizontalLinesSpaceBetween * i);
        // teraz pionowe - x const i y1 y2 min max
        int verticalLinesSpaceBetween = sizeX / mapSizeX;
        for (int i = 0; i <= mapSizeX; i++)
            graphics.drawLine(verticalLinesSpaceBetween * i, 0, verticalLinesSpaceBetween * i, sizeY);
        // teraz rysowanie mapy. 0 0 w lewym górnym rogu
        for (int i = 0; i < mapSizeX; i++) {
            for (int j = 0; j < mapSizeY; j++) {
                graphics.setColor(getColor(this.map.getField(i, j)));
                graphics.fillRect(i * verticalLinesSpaceBetween + 1, j * horizontalLinesSpaceBetween + 1, verticalLinesSpaceBetween - 2, horizontalLinesSpaceBetween - 2);
            }

        }

        return imageToDraw;
    }

    public FieldTypes getCurrentSelectedFieldType() {
        if (this.wallButton.isSelected()) return FieldTypes.Wall;
        else if (this.enterButton.isSelected()) return FieldTypes.Enter;
        else if (this.exitButton.isSelected()) return FieldTypes.Exit;
        else return FieldTypes.Free;
    }

    public Color getColor(FieldTypes fieldtype) {
        if (fieldtype == FieldTypes.Wall) return wallColor;
        else if (fieldtype == FieldTypes.Enter) return enterColor;
        else if (fieldtype == FieldTypes.Exit) return exitColor;
        else if (fieldtype == FieldTypes.Way) return wayColor;
        else return freeColor;
    }

}


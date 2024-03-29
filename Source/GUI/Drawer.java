import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Vector;
import java.util.function.BiFunction;

public class Drawer {
    protected JPanel mainPanel;
    protected JPanel drawingPanel;
    BiFunction<Coords, Coords, Double> heuristicsFunction;
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
    protected JLabel drawingLabel;
    protected JButton resetButton;
    protected JSlider randSlider;
    protected JButton randomButton;
    protected JLabel randomLabel;
    protected JTextField iterationsField;
    protected JSlider itSlider;

    protected Color freeColor;
    protected Color wallColor;
    protected Color enterColor;
    protected Color exitColor;
    protected Color wayColor;
    protected Map map;

    public void setMap(Map map) { // call from other place
        this.map = map;
    }

    protected void initColors() {
        this.enterColor = Color.GREEN;
        this.exitColor = Color.RED;
        this.freeColor = Color.WHITE;
        this.wallColor = Color.BLACK;
        this.wayColor = Color.BLUE;
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
                switch (getCurrentSelectedFieldType()) {
                    case Free:
                        InitMap.Mapa[row][column] = 0;
                        break;

                    case Exit:
                        InitMap.Mapa[row][column] = 3;
                        break;

                    case Wall:
                        InitMap.Mapa[row][column] = 2;
                        break;

                    case Enter:
                        InitMap.Mapa[row][column] = 1;
                        break;

                    default:
                        break;

                }
                // for (int i=0; i<3; i++) System.out.println(InitMap.Mapa[0][i]+" "+InitMap.Mapa[1][i]+" "+InitMap.Mapa[2][i]);
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
                switch (getCurrentSelectedFieldType()) {
                    case Free:
                        InitMap.Mapa[row][column] = 0;
                        break;

                    case Exit:
                        InitMap.Mapa[row][column] = 3;
                        break;

                    case Wall:
                        InitMap.Mapa[row][column] = 2;
                        break;

                    case Enter:
                        InitMap.Mapa[row][column] = 1;
                        break;

                    default:
                        break;

                }


            }
        });

        executeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!map.viableMap()) {
                    JOptionPane.showMessageDialog(mainPanel, "Moze byc tylko jedno wejscie oraz jedno wyjscie");
                    map.resetMap();
                    return;
                }
                map.softResetMap();
                if (heuristicBox.getSelectedIndex() == 0)
                    heuristicsFunction = (a, b) -> Double.valueOf(Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)));
                if (heuristicBox.getSelectedIndex() == 1)
                    heuristicsFunction = (a, b) -> Double.valueOf(
                            Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
                InitMap.importMap(map);

                A.heuristicsFunction = heuristicsFunction;

                Calendar before = Calendar.getInstance();
                A.result = false;
                InitMap.setMaxIter((int) Math.pow((double) 2, (double) itSlider.getValue()));
                Vector<Coords> vector = null;
                try {
                    vector = A.A();
                } catch (Exception ex) {
                    System.out.println("Nie jest możliwe znalezienie scieżki");
                }
                Calendar after = Calendar.getInstance();
                long difference = after.getTimeInMillis() - before.getTimeInMillis();
                double seconds = (double) difference / (double) 1000;
                resultField.setText("Wynik ".concat(Boolean.toString(A.result)).concat(" W czasie :").concat(Long.toString(difference)).concat(" milisekund"));
                try {
                    vector.forEach(coords -> map.setTile(coords.x, coords.y, FieldTypes.Way));
                } catch (Exception ex) {
                    System.out.println("Blad rysowania sciezki");
                }


                drawingLabel.setIcon(new ImageIcon(recalculateImage()));


            }
        });

        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                map.resetMap();
                drawingLabel.setIcon(new ImageIcon(recalculateImage()));
            }
        });
        exportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                seedField.setText(map.toString());
            }
        });
        importButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                map.fromString(seedField.getText());
                drawingLabel.setIcon(new ImageIcon(recalculateImage()));
            }
        });
        randomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double randSeed = (double) randSlider.getValue();
                map.randomizeMap(randSeed);
                drawingLabel.setIcon(new ImageIcon(recalculateImage()));
            }
        });
        itSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int iterations = (int) Math.pow((double) 2, (double) itSlider.getValue());
                iterationsField.setText("Liczba iteracji " + Long.toString(iterations));
            }
        });
    }

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
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setMinimumSize(new Dimension(300, 300));
        mainPanel.setPreferredSize(new Dimension(51, 51));
        upPanel = new JPanel();
        upPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(upPanel, BorderLayout.NORTH);
        seedField = new JTextField();
        seedField.setText("");
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
        resetButton = new JButton();
        resetButton.setText("Reset map");
        leftPanel.add(resetButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        randSlider = new JSlider();
        randSlider.setMaximum(60);
        randSlider.setMinimum(30);
        randSlider.setValue(45);
        leftPanel.add(randSlider, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        randomLabel = new JLabel();
        randomLabel.setText("Rand strength");
        leftPanel.add(randomLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        randomButton = new JButton();
        randomButton.setText("Randomize map");
        leftPanel.add(randomButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        iterationsField = new JTextField();
        iterationsField.setText("Liczba iteracji");
        leftPanel.add(iterationsField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        itSlider = new JSlider();
        itSlider.setMaximum(20);
        itSlider.setMinimum(8);
        itSlider.setValue(10);
        leftPanel.add(itSlider, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Pythagoras");
        defaultComboBoxModel1.addElement("Manhattan");
        heuristicBox.setModel(defaultComboBoxModel1);
        settingPanel.add(heuristicBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        executeButton = new JButton();
        executeButton.setText("Find route");
        settingPanel.add(executeButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultField = new JTextField();
        resultField.setText("Result");
        settingPanel.add(resultField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        drawingPanel = new JPanel();
        drawingPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        drawingPanel.setMinimumSize(new Dimension(200, 200));
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
        BufferedImage imageToDraw = /*UIUtil.createImage(sizeX, sizeY, BufferedImage.TYPE_3BYTE_BGR)*/new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_3BYTE_BGR);
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


package histogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Histogram extends JFrame {
    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPane1;
    private JSplitPane jSplitPane1;
    private JButton loadButton;
    private JPanel mainPanel;
    private JTextField numField;
    private JButton showButton;
    private JTextArea sourceArea;
    private JPanel topPanel;
    private HistogramPanel outPanel;

    public Histogram(){
        initComponents();
        this.setTitle("COP3330 Sentence Histogram by Matthew Bautista");
    }

    public static void main(String args[]){
        try{
            for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch( ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException ex){
            Logger.getLogger(Histogram.class.getName()).log(
                    Level.SEVERE, null, ex
            );

        }
        EventQueue.invokeLater(()->{
            new Histogram().setVisible(true);
        });
    }

    private void initComponents(){
        topPanel = new JPanel();
        loadButton = new JButton();
        showButton = new JButton();
        numField = new JTextField();
        mainPanel = new JPanel();
        jSplitPane1 = new JSplitPane();
        jScrollPane1 = new JScrollPane();
        sourceArea = new JTextArea();
        outPanel = new HistogramPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        topPanel.setBackground((new Color(230,240,255)));
        topPanel.setPreferredSize(new Dimension(686,40));

        loadButton.setText("Load File");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        topPanel.add(loadButton);

        showButton.setText("Show Histo for Sentence");
        showButton.setEnabled(false);
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });
        numField.setColumns(3);
        numField.setEnabled(false);
        numField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                numFieldActionPerformed(evt);
            }
        });
        topPanel.add(numField);

        getContentPane().add(topPanel,BorderLayout.PAGE_START);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sourceArea.setColumns(20);
        sourceArea.setLineWrap(true);
        sourceArea.setRows(5);
        jScrollPane1.setViewportView(sourceArea);

        jSplitPane1.setLeftComponent(jScrollPane1);

        outPanel.setBackground(new Color(230,230,230));
        outPanel.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent evt) {
                outPanelComponentResized(evt);
            }
        });
        GroupLayout outPanelLayout = new GroupLayout(outPanel);
        outPanel.setLayout(outPanelLayout);
        outPanelLayout.setHorizontalGroup(
                outPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0,128,Short.MAX_VALUE)
        );
        outPanelLayout.setVerticalGroup(
                outPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0,256, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(outPanel);
        mainPanel.add(jSplitPane1);
        getContentPane().add(mainPanel,BorderLayout.CENTER);
        pack();
    }
    private void loadButtonActionPerformed(ActionEvent evt){

    }
    private void showButtonActionPerformed(ActionEvent evt){

    }
    private void numFieldActionPerformed(ActionEvent evt){
        showButtonActionPerformed(evt);
    }
    private void outPanelComponentResized(ComponentEvent evt){
        outPanel.showHisto();
    }
}

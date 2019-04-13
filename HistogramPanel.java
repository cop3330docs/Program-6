package histogram;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistogramPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<String> sents;
    private int snum;

    public String readFile(File file){
        sents = new ArrayList<>();
        snum = -1;
        clearDisplay(this.getGraphics());
        StringBuilder sb = new StringBuilder();

        try{
            Scanner scanner = new Scanner(new FileInputStream(file));
            while(scanner.hasNextLine()){
                String s = scanner.nextLine().trim();
                if(s.length() > 0){
                    sents.add(s);
                }
            }
            scanner.close();

            for(int i = 0;i<sents.size();i++){
                sb.append(i + " : " + sents.get(i) + "\n\n");
            }
        }catch(FileNotFoundException ex){
            Logger.getLogger(HistogramPanel.class.getName()).log(
                    Level.SEVERE,null,ex
            );
        }
        return sb.toString().trim();
    }

    @Override
    protected void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        if(sents!=null && snum>=0 && snum<sents.size()){
            showHisto(snum, true);
        }
    }

    public void showHisto(){
        this.setBackground(Color.white);
        showHisto(snum,false);
    }
    public void showHisto(int n, boolean b){
        if(sents != null && n >= 0 && n < sents.size()){
            snum = n;
            Graphics gc = this.getGraphics();
            clearDisplay(gc);
            drawLines(gc);
            drawHisto(gc);
        }
        else if(b && sents != null){
            JOptionPane.showMessageDialog(this, "Sentence index out of range");
        }
    }
    private void clearDisplay(Graphics gc){
        gc.setColor(Color.WHITE);
        gc.fillRect(0,0, this.getWidth(), this.getHeight());
    }

    private void drawLines(Graphics gc){

    }
    private void drawHisto(Graphics gc){

    }
}

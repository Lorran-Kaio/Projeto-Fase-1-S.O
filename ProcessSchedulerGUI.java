import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ProcessSchedulerGUI extends JFrame {
    private ProcessPanel processPanel;
    private CPU cpu;
    private ProcessGenerator generator;
    private Scheduler scheduler;
    private List<Process> allProcesses;
    
    public ProcessSchedulerGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        generator = new ProcessGenerator();
        scheduler = new Scheduler(Scheduler.Algorithm.RR, 5);
        allProcesses = new ArrayList<>();
        cpu = new CPU(scheduler, generator, this, allProcesses);
        
        processPanel = new ProcessPanel();
        add(processPanel, BorderLayout.CENTER);
        
        setVisible(true);
        
        
        startSimulation();
    }
    
    public void startSimulation() {
        new Thread(() -> cpu.execute()).start();
    }
    
    public void updateProcessDisplay(Process executingProcess) {
        processPanel.updateProcesses(allProcesses, executingProcess);
    }
    
    class ProcessPanel extends JPanel {
        private java.util.List<Process> processes = new ArrayList<>();
        private Process executingProcess;
        
        public void updateProcesses(List<Process> processes, Process executingProcess) {
            this.processes = processes;
            this.executingProcess = executingProcess;
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            
            if (processes.isEmpty()) return;
            
            int cubeSize = 80;
            int spacing = 20;
            int totalWidth = processes.size() * cubeSize + (processes.size() - 1) * spacing;
            int startX = (getWidth() - totalWidth) / 2;
            int y = (getHeight() - cubeSize) / 2;
            
            
            processes.sort(Comparator.comparingInt(Process::getId));
            
            for (int i = 0; i < processes.size(); i++) {
                Process p = processes.get(i);
                int x = startX + i * (cubeSize + spacing);
                
                
                if (p == executingProcess) {
                    g.setColor(Color.BLUE); 
                } else if (p.finished()) {
                    g.setColor(Color.GREEN); 
                } else {
                    g.setColor(Color.RED); 
                }
                
                
                g.fillRect(x, y, cubeSize, cubeSize);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cubeSize, cubeSize);
                
               
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                
                
                String idText = "ID: " + p.getId();
                String instText = "Inst: " + p.getInstructions();
                
                FontMetrics fm = g.getFontMetrics();
                int idWidth = fm.stringWidth(idText);
                int instWidth = fm.stringWidth(instText);
                
                g.drawString(idText, x + (cubeSize - idWidth) / 2, y + cubeSize / 2 - 10);
                g.drawString(instText, x + (cubeSize - instWidth) / 2, y + cubeSize / 2 + 10);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProcessSchedulerGUI::new);
    }
}
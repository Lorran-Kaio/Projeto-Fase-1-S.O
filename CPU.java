class CPU {
    private Scheduler scheduler;
    private ProcessGenerator generator;
    private ProcessSchedulerGUI gui;
    private java.util.List<Process> allProcesses;
    
    public CPU(Scheduler scheduler, ProcessGenerator generator, ProcessSchedulerGUI gui, java.util.List<Process> allProcesses) {
        this.scheduler = scheduler;
        this.generator = generator;
        this.gui = gui;
        this.allProcesses = allProcesses;
    }
    
    public void execute() {
        
        // i < 5 = quantidade de processos
        for (int i = 0; i < 5; i++) {
            Process newProcess = generator.generateProcess();
            scheduler.addProcess(newProcess);
            allProcesses.add(newProcess);
        }
        
        
        updateGUI(null);
        
        // Tempo antes de inicializar 
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        while (scheduler.existProcesses()) {
            Process process = scheduler.nextProcess();
            
            if (process == null) continue;
            
            
            updateGUI(process);
            
            System.out.println("Executing process: " + process.getId());
            
            if (scheduler.getAlgorithm() == Scheduler.Algorithm.RR) {
                for (int i = 0; i < scheduler.getQuantum(); i++) {
                    if (process.finished()) break;
                    // Se o processo for RR, executa as instrunções de acordo com o quantum
                    process.executeInstructions();
                    
                    
                    updateGUI(process);
                    try {
                        // Animação da execução das instrunções
                        Thread.sleep(100); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                if (!process.finished()) {
                    scheduler.addProcess(process);
                    System.out.println("Process " + process.getId() + " interrupted. Instructions missing: " + 
                                      process.getInstructions());
                } else {
                    System.out.println("Process " + process.getId() + " finished");
                }
            } else {
                while (!process.finished()) {
                    process.executeInstructions();
                    
                    
                    updateGUI(process);
                    try {
                        // Animação da execução das instrunções
                        Thread.sleep(100); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Process " + process.getId() + " finished");
            }
            
            
            try {
                //tempo entre a execução de cada processo
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            
            updateGUI(null);
        }
    }
    
    private void updateGUI(Process executingProcess) {
        if (gui != null) {
            gui.updateProcessDisplay(executingProcess);
        }
    }
}
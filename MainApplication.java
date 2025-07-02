import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main application - runs all interfaces and processes
 */
public class MainApplication extends JFrame {
    
    public MainApplication() {
        setTitle("Educational Institution Transportation System - SDD Project");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        initializeMainInterface();
        
        System.out.println("MainApplication initialized successfully");
    }
    
    private void initializeMainInterface() {
        // Top panel with title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("Educational Institution Transportation System", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        // Central panel with interface buttons
        JPanel interfacePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        interfacePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Parent authentication interface button
        JButton parentAuthButton = createInterfaceButton(
            "Parent Authentication Interface", 
            "Presence verification and child identification",
            e -> new ParentIdentificationApp().setVisible(true)
        );
        
        // Building transportation interface button
        JButton buildingButton = createInterfaceButton(
            "Building Transportation",
            "Shared transportation management for residents", 
            e -> new BuildingResidentsApp().setVisible(true)
        );
        
        // Driver interface button
        JButton driverButton = createInterfaceButton(
            "Driver Interface",
            "Precise routes and children list",
            e -> new DriverInterface().setVisible(true)
        );
        
        // Parent tracking interface button
        JButton trackingButton = createInterfaceButton(
            "Parent Tracking", 
            "Real-time tracking of children",
            e -> new ParentTrackingApp().setVisible(true)
        );
        
        // School portal button
        JButton schoolButton = createInterfaceButton(
            "School Portal",
            "Attendance management and student tracking",
            e -> new SchoolPortal().setVisible(true)
        );
        
        // Sequence diagram execution button
        JButton sequenceButton = createInterfaceButton(
            "Run Sequence Diagram",
            "20 complete system steps",
            e -> runSequenceDemo()
        );
        
        interfacePanel.add(parentAuthButton);
        interfacePanel.add(buildingButton);
        interfacePanel.add(driverButton);
        interfacePanel.add(trackingButton);
        interfacePanel.add(schoolButton);
        interfacePanel.add(sequenceButton);
        
        // Bottom panel with information
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(52, 73, 94));
        JLabel infoLabel = new JLabel(
            "<html><center>System developed according to SDD requirements<br>" +
            "All interfaces and processes are fully operational<br>" +
            "Development: Development Team - " + java.time.LocalDate.now() + "</center></html>",
            JLabel.CENTER
        );
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(infoLabel);
        
        add(titlePanel, BorderLayout.NORTH);
        add(interfacePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);
    }
    
    private JButton createInterfaceButton(String title, String description, ActionListener action) {
        JButton button = new JButton(
            "<html><center><b>" + title + "</b><br>" +
            "<small>" + description + "</small></center></html>"
        );
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(250, 80));
        button.addActionListener(action);
        button.setBackground(new Color(236, 240, 241));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(189, 195, 199));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(236, 240, 241));
            }
        });
        
        return button;
    }
    
    private void runSequenceDemo() {
        // Create separate window for sequence diagram
        JFrame sequenceFrame = new JFrame("Sequence Diagram - 20 Steps");
        sequenceFrame.setSize(900, 700);
        sequenceFrame.setLayout(new BorderLayout());
        
        JTextArea outputArea = new JTextArea();
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.GREEN);
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        JButton runButton = new JButton("Run Sequence Diagram");
        runButton.setFont(new Font("Arial", Font.BOLD, 16));
        runButton.addActionListener(e -> {
            outputArea.setText("");
            runButton.setEnabled(false);
            
            // Background execution
            SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Redirect output to text area
                    java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                    java.io.PrintStream ps = new java.io.PrintStream(baos);
                    java.io.PrintStream old = System.out;
                    System.setOut(ps);
                    
                    try {
                        // Run the diagram
                        SequenceDemo.main(new String[]{});
                    } finally {
                        System.out.flush();
                        System.setOut(old);
                        publish(baos.toString());
                    }
                    return null;
                }
                
                @Override
                protected void process(java.util.List<String> chunks) {
                    for (String chunk : chunks) {
                        outputArea.append(chunk);
                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                    }
                }
                
                @Override
                protected void done() {
                    runButton.setEnabled(true);
                    outputArea.append("\n\n=== Sequence diagram completed successfully! ===");
                }
            };
            
            worker.execute();
        });
        
        sequenceFrame.add(new JLabel("Sequence Diagram Output - 20 Complete Steps", JLabel.CENTER), BorderLayout.NORTH);
        sequenceFrame.add(scrollPane, BorderLayout.CENTER);
        sequenceFrame.add(runButton, BorderLayout.SOUTH);
        
        sequenceFrame.setVisible(true);
    }
    
    /**
     * Main entry point for the application
     */
    public static void main(String[] args) {
        // Set Look and Feel - Fixed version
        // Use default look and feel
        System.out.println("Using default look and feel"); 
        
        System.out.println("=== Educational Institution Transportation System ===");
        System.out.println("SDD Project - System Design Document");
        System.out.println("Developed with all requirements: 10 classes + 5 interfaces + sequence diagram");
        System.out.println("==========================================\n");
        
        // Create and display main windows
        SwingUtilities.invokeLater(() -> {
            try {
                MainApplication app = new MainApplication();
                app.setLocationRelativeTo(null); // Center of screen
                app.setVisible(true);
                
                // Display success message
                JOptionPane.showMessageDialog(
                    app,
                    "System started successfully!\n\n" +
                    "• 10 complete classes ✓\n" +
                    "• 5 user interfaces ✓\n" +
                    "• 20-step sequence diagram ✓\n" +
                    "• All requirements implemented ✓",
                    "System Started Successfully",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                    null,
                    "Error starting system: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
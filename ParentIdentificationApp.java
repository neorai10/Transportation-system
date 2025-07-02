import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * Parent identification interface - Figure 1
 */
public class ParentIdentificationApp extends JFrame {
    private JTextField childIdField;
    private JButton authenticateButton;
    private JLabel statusLabel;
    private ChildIdentificationManager identificationManager;

    public ParentIdentificationApp() {
        setTitle("Parent Identification Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        identificationManager = new ChildIdentificationManager("MGR_001", "VEHICLE_001");

        initializeComponents();
        System.out.println("ParentIdentificationApp initialized");
    }

    private void initializeComponents() {
        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Child ID:"));
        childIdField = new JTextField(15);
        topPanel.add(childIdField);

        // Center panel
        JPanel centerPanel = new JPanel(new FlowLayout());
        authenticateButton = new JButton("Authenticate Presence");
        authenticateButton.setFont(new Font("Arial", Font.BOLD, 14));
        authenticateButton.addActionListener(new AuthenticateActionListener());
        centerPanel.add(authenticateButton);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        statusLabel = new JLabel("Status: Ready for authentication");
        statusLabel.setForeground(Color.BLUE);
        bottomPanel.add(statusLabel);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class AuthenticateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String childId = childIdField.getText().trim();
            if (!childId.isEmpty()) {
                statusLabel.setText("Authenticating child: " + childId);
                statusLabel.setForeground(Color.ORANGE);
                
                // Start authentication process
                boolean authenticated = identificationManager.verifyChildExists(childId);
                
                if (authenticated) {
                    statusLabel.setText("Authentication successful for child: " + childId);
                    statusLabel.setForeground(Color.GREEN);
                } else {
                    statusLabel.setText("Authentication error for child: " + childId);
                    statusLabel.setForeground(Color.RED);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ParentIdentificationApp().setVisible(true);
        });
    }
}

/**
 * Building residents transportation interface - Figure 2
 */
class BuildingResidentsApp extends JFrame {
    private JList<String> residentsList;
    private JButton orderRideButton;
    private JLabel statusLabel;

    public BuildingResidentsApp() {
        setTitle("Building Residents Transportation");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeComponents();
        System.out.println("BuildingResidentsApp initialized");
    }

    private void initializeComponents() {
        // Residents list
        String[] residents = {
            "Apartment 1 - Cohen Family (2 children)",
            "Apartment 3 - Levy Family (1 child)",
            "Apartment 5 - Abraham Family (3 children)",
            "Apartment 8 - David Family (1 child)"
        };
        
        residentsList = new JList<>(residents);
        residentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(residentsList);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Order ride button
        orderRideButton = new JButton("Order Shared Ride");
        orderRideButton.setFont(new Font("Arial", Font.BOLD, 14));
        orderRideButton.addActionListener(e -> orderSharedRide());

        // Status
        statusLabel = new JLabel("Select residents and order shared ride");

        add(new JLabel("Building Residents List:", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(orderRideButton);
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void orderSharedRide() {
        java.util.List<String> selectedResidents = residentsList.getSelectedValuesList();
        if (!selectedResidents.isEmpty()) {
            statusLabel.setText("Shared ride ordered for " + selectedResidents.size() + " families");
            System.out.println("Shared ride ordered for: " + selectedResidents);
        } else {
            statusLabel.setText("Please select at least one family");
        }
    }
}

/**
 * Driver interface - Figure 3
 */
class DriverInterface extends JFrame {
    private JTextArea routeArea;
    private JList<String> childrenList;
    private JLabel statusLabel;

    public DriverInterface() {
        setTitle("Driver Interface");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeComponents();
        System.out.println("DriverInterface initialized");
    }

    private void initializeComponents() {
        // Detailed route
        routeArea = new JTextArea();
        routeArea.setText("Today's Route:\n" +
                         "1. Herzl Street 15 - Pick up Yossi (07:30)\n" +
                         "2. Rothschild Boulevard 45 - Pick up Noa (07:35)\n" +
                         "3. Dizengoff Street 12 - Pick up Or (07:40)\n" +
                         "4. Tel Aviv Elementary School (08:00)");
        routeArea.setEditable(false);
        routeArea.setFont(new Font("Arial", Font.PLAIN, 12));

        // Children list at stations
        String[] children = {
            "Yossi Cohen - Station 1 - Status: Waiting",
            "Noa Levy - Station 2 - Status: Waiting", 
            "Or David - Station 3 - Status: Waiting"
        };
        childrenList = new JList<>(children);

        statusLabel = new JLabel("Ready to start route - " + LocalDateTime.now().toLocalTime());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Detailed Route:", JLabel.CENTER), BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(routeArea), BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Children at Stations:", JLabel.CENTER), BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(childrenList), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(300);

        add(splitPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
}

/**
 * Parent tracking interface - Figure 4
 */
class ParentTrackingApp extends JFrame {
    private JButton trackButton;
    private JTextArea mapArea;
    private JTextField childIdField;
    private LocationTracker locationTracker;

    public ParentTrackingApp() {
        setTitle("Parent Tracking Interface");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        locationTracker = new LocationTracker("LT_001", "VEHICLE_001");
        initializeComponents();
        System.out.println("ParentTrackingApp initialized");
    }

    private void initializeComponents() {
        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Child ID:"));
        childIdField = new JTextField(10);
        topPanel.add(childIdField);
        
        trackButton = new JButton("Track Child");
        trackButton.setFont(new Font("Arial", Font.BOLD, 14));
        trackButton.addActionListener(e -> startTracking());
        topPanel.add(trackButton);

        // Map (simulation)
        mapArea = new JTextArea();
        mapArea.setText("=== Real-time Tracking Map ===\n\n" +
                       "🚌 Bus: Herzl Street 25\n" +
                       "📍 Last location: 32.0853, 34.7818\n" +
                       "⏰ Update time: " + LocalDateTime.now().toLocalTime() + "\n\n" +
                       "🎯 Next destination: Elementary School\n" +
                       "⏱️ Estimated arrival: 15 minutes");
        mapArea.setEditable(false);
        mapArea.setFont(new Font("Arial", Font.PLAIN, 12));

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(mapArea), BorderLayout.CENTER);
    }

    private void startTracking() {
        String childId = childIdField.getText().trim();
        if (!childId.isEmpty()) {
            locationTracker.startTracking(childId);
            Location currentLocation = locationTracker.getCurrentLocation();
            
            mapArea.setText("=== Active tracking for child: " + childId + " ===\n\n" +
                           "🚌 Bus is at: " + currentLocation.getDescription() + "\n" +
                           "📍 Coordinates: " + currentLocation.getLatitude() + ", " + currentLocation.getLongitude() + "\n" +
                           "⏰ Update time: " + LocalDateTime.now().toLocalTime() + "\n\n" +
                           "✅ Tracking active\n" +
                           "🔄 Updates automatically every 30 seconds");
        }
    }
}

/**
 * Educational institution portal - Figure 5
 */
class SchoolPortal extends JFrame {
    private JTable attendanceTable;
    private JLabel summaryLabel;

    public SchoolPortal() {
        setTitle("Educational Institution Management - School Portal");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeComponents();
        System.out.println("SchoolPortal initialized");
    }

    private void initializeComponents() {
        // Attendance table
        String[] columnNames = {"Student ID", "Name", "Class", "Arrival Status", "Arrival Time", "Parent"};
        Object[][] data = {
            {"001", "Yossi Cohen", "A1", "Arrived", "07:45", "Father - 050-1234567"},
            {"002", "Noa Levy", "A1", "En Route", "Expected 08:00", "Mother - 050-2345678"},
            {"003", "Or David", "B2", "Arrived", "07:50", "Mother - 050-3456789"},
            {"004", "Dana Abraham", "B2", "Not Arrived", "---", "Father - 050-4567890"},
            {"005", "Eitan Samuel", "C1", "Arrived", "07:40", "Grandmother - 050-5678901"}
        };

        attendanceTable = new JTable(data, columnNames);
        attendanceTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(attendanceTable);

        // Summary
        summaryLabel = new JLabel("Summary: 5 students registered | 3 arrived | 1 en route | 1 not arrived", JLabel.CENTER);
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(new JLabel("Student Attendance Tracking", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(summaryLabel, BorderLayout.SOUTH);
    }
}
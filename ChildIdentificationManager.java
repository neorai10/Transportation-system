import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ChildIdentificationManager class - manages child identification
 */
public class ChildIdentificationManager {
    private String managerId;
    private Map<String, String> activeIdentifications;
    private String currentVehicleId;
    private SystemStatus systemStatus;

    // Constructor
    public ChildIdentificationManager(String managerId, String vehicleId) {
        this.managerId = managerId;
        this.currentVehicleId = vehicleId;
        this.activeIdentifications = new HashMap<>();
        this.systemStatus = SystemStatus.ACTIVE;
        System.out.println("ChildIdentificationManager " + managerId + " initialized for vehicle " + vehicleId);
    }

    /**
     * Start identification process - step 1
     */
    public void trackChild(String childId) {
        System.out.println("Step 1: ChildIdentificationManager.trackChild(" + childId + ")");
        startIdentificationProcess();
        
        // Check if child exists in system
        Child child = getChildById(childId);
        boolean childExists = child.verifyChildExists(childId);
        
        if (childExists) {
            activeIdentifications.put(childId, "TRACKING_STARTED");
            System.out.println("Child " + childId + " added to active tracking");
        }
    }

    /**
     * Start identification process
     */
    public void startIdentificationProcess() {
        systemStatus = SystemStatus.ACTIVE;
        System.out.println("Identification process started - Manager: " + managerId);
    }

    /**
     * Process child entry
     */
    public boolean processChildEntry(String childId) {
        if (activeIdentifications.containsKey(childId)) {
            activeIdentifications.put(childId, "ENTERED");
            System.out.println("Child entry processed for: " + childId);
            return true;
        }
        return false;
    }

    /**
     * Process child exit
     */
    public boolean processChildExit(String childId) {
        if (activeIdentifications.containsKey(childId)) {
            activeIdentifications.put(childId, "EXITED");
            System.out.println("Child exit processed for: " + childId);
            return true;
        }
        return false;
    }

    /**
     * Generate identification report
     */
    public Report generateIdentificationReport() {
        Report report = new Report("IDENTIFICATION_REPORT_" + System.currentTimeMillis());
        report.setManagerId(managerId);
        report.setVehicleId(currentVehicleId);
        report.setActiveIdentifications(new HashMap<>(activeIdentifications));
        report.setTimestamp(LocalDateTime.now());
        
        System.out.println("Generated identification report: " + report.getReportId());
        return report;
    }

    /**
     * Track child - step 4
     */
    public void trackChild(String childId, LocationTracker locationTracker) {
        System.out.println("Step 4: ChildIdentificationManager → LocationTracker.startTracking(" + childId + ")");
        locationTracker.startTracking(childId);
    }

    /**
     * Verify child exists
     */
    public boolean verifyChildExists(String childId) {
        Child child = getChildById(childId);
        return child != null && child.verifyChildExists(childId);
    }

    /**
     * Report child status - step 14
     */
    public void reportChildStatus(String childId, ChildStatus status, Location location) {
        System.out.println("Step 14: FaceRecognitionAndControlSystem → ChildIdentificationManager.reportChildStatus(" + 
                          childId + ", " + status + ", " + location + ")");
        
        activeIdentifications.put(childId, status.toString());
        
        // Step 15: Update child status
        Child child = getChildById(childId);
        System.out.println("Step 15: ChildIdentificationManager → Child.updateStatus(" + status + ")");
        child.updateStatus(status);
    }

    /**
     * Confirm tracking active - step 16
     */
    public void confirmTrackingActive(String childId, LocationTracker locationTracker) {
        System.out.println("Step 16: ChildIdentificationManager → LocationTracker.confirmTrackingActive(" + childId + ")");
        locationTracker.confirmTrackingActive(childId);
    }

    /**
     * Send location update - step 17
     */
    public void sendLocationUpdate(String childId, Location location, ParentNotificationProxy notificationProxy) {
        System.out.println("Step 17: ChildIdentificationManager → ParentNotificationProxy.sendLocationUpdate(" + 
                          "parentId, " + childId + ", " + location + ", " + LocalDateTime.now() + ")");
        
        Child child = getChildById(childId);
        String parentId = child.getParentId();
        notificationProxy.sendLocationUpdate(parentId, childId, location, LocalDateTime.now());
    }

    /**
     * Send tracking confirmation - step 19
     */
    public void sendTrackingConfirmation(String childId, ParentNotificationProxy notificationProxy) {
        System.out.println("Step 19: ChildIdentificationManager → ParentNotificationProxy.sendTrackingConfirmation(" + 
                          "parentId, " + childId + ")");
        
        Child child = getChildById(childId);
        String parentId = child.getParentId();
        notificationProxy.sendTrackingConfirmation(parentId, childId);
    }

    /**
     * Get child by ID
     */
    private Child getChildById(String childId) {
        // Simulation of getting child from database
        return new Child(childId, "Child_" + childId, 8, "SCHOOL_001", "PARENT_" + childId);
    }

    // Getters
    public String getManagerId() { return managerId; }
    public Map<String, String> getActiveIdentifications() { return activeIdentifications; }
    public String getCurrentVehicleId() { return currentVehicleId; }
    public SystemStatus getSystemStatus() { return systemStatus; }
}

/**
 * Report class
 */
class Report {
    private String reportId;
    private String managerId;
    private String vehicleId;
    private Map<String, String> activeIdentifications;
    private LocalDateTime timestamp;

    public Report(String reportId) {
        this.reportId = reportId;
    }

    // Getters and Setters
    public String getReportId() { return reportId; }
    public String getManagerId() { return managerId; }
    public void setManagerId(String managerId) { this.managerId = managerId; }
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    public Map<String, String> getActiveIdentifications() { return activeIdentifications; }
    public void setActiveIdentifications(Map<String, String> activeIdentifications) { 
        this.activeIdentifications = activeIdentifications; 
    }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
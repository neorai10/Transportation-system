/**
 * Parent class - represents a parent in the transportation system
 */
public class Parent {
    private String parentId;
    private String name;
    private String phoneNumber;
    private String email;

    // Constructor
    public Parent(String parentId, String name) {
        this.parentId = parentId;
        this.name = name;
        this.phoneNumber = "050-" + (1000000 + Math.abs(parentId.hashCode()) % 9000000);
        this.email = name.toLowerCase().replace(" ", ".") + "@email.com";
        System.out.println("Parent " + name + " (ID: " + parentId + ") initialized");
    }

    /**
     * Track child - start the process
     */
    public void trackChild(String childId, ChildIdentificationManager manager) {
        System.out.println("Step 1: Parent → ChildIdentificationManager.trackChild(" + childId + ")");
        System.out.println("Parent " + name + " requesting to track child: " + childId);
        manager.trackChild(childId);
    }

    /**
     * Receive notification - Step 18
     */
    public void receiveNotification(String title, String message) {
        System.out.println("Step 18: Parent.receiveNotification(\"" + title + "\")");
        System.out.println("=== Notification for parent " + name + " ===");
        System.out.println("Title: " + title);
        System.out.println("Message: " + message);
        System.out.println("Reception time: " + java.time.LocalDateTime.now());
        System.out.println("=============================");
    }

    /**
     * Display tracking status - Step 20
     */
    public void displayTrackingStatus(String status) {
        System.out.println("Step 20: Parent.displayTrackingStatus(\"" + status + "\")");
        System.out.println("=== Tracking status for " + name + " ===");
        System.out.println("Status: " + status);
        System.out.println("Update time: " + java.time.LocalDateTime.now());
        System.out.println("===================================");
    }

    /**
     * Request location update
     */
    public void requestLocationUpdate(String childId) {
        System.out.println("Parent " + name + " requesting location update for child: " + childId);
    }

    /**
     * Set notification preferences
     */
    public void setNotificationPreferences(boolean smsEnabled, boolean pushEnabled, boolean emailEnabled) {
        System.out.println("Notification preferences updated for parent " + name + 
                          " - SMS: " + smsEnabled + 
                          ", Push: " + pushEnabled + 
                          ", Email: " + emailEnabled);
    }

    /**
     * Get tracking history
     */
    public void viewTrackingHistory(String childId) {
        System.out.println("Displaying tracking history for child " + childId + " requested by parent " + name);
    }

    // Getters
    public String getParentId() { return parentId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    // Setters
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Parent{" +
                "parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
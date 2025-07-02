import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CameraProxy class - Proxy for cameras
 */
class CameraProxy {
    private String proxyId;
    private List<String> connectedCameras;

    public CameraProxy(String proxyId) {
        this.proxyId = proxyId;
        this.connectedCameras = new ArrayList<>();
        initializeCameras();
    }

    private void initializeCameras() {
        connectedCameras.add("CAM_001");
        connectedCameras.add("CAM_002");
        connectedCameras.add("CAM_003");
        System.out.println("CameraProxy " + proxyId + " initialized with " + connectedCameras.size() + " cameras");
    }

    /**
     * Capture image through Proxy
     */
    public Image captureImage() {
        System.out.println("CameraProxy capturing image through proxy...");
        // Select available camera
        if (!connectedCameras.isEmpty()) {
            String selectedCamera = connectedCameras.get(0);
            Image image = new Image("PROXY_IMG_" + selectedCamera + "_" + System.currentTimeMillis());
            System.out.println("Image captured via proxy from camera: " + selectedCamera);
            return image;
        }
        return null;
    }

    /**
     * Get camera status
     */
    public CameraStatus getCameraStatus() {
        if (!connectedCameras.isEmpty()) {
            String cameraId = connectedCameras.get(0);
            return new CameraStatus(cameraId, true, "1920x1080", LocalDateTime.now());
        }
        return null;
    }

    public String getProxyId() { return proxyId; }
    public List<String> getConnectedCameras() { return connectedCameras; }
}

/**
 * GPSServiceProxy class - Proxy for GPS service
 */
class GPSServiceProxy {
    private String serviceId;

    public GPSServiceProxy(String serviceId) {
        this.serviceId = serviceId;
        System.out.println("GPSServiceProxy " + serviceId + " initialized");
    }

    /**
     * Get current GPS location - step 6
     */
    public Location getCurrentGPSLocation() {
        System.out.println("Step 6: GPSServiceProxy.getCurrentGPSLocation()");
        
        // Simulation of GPS location retrieval
        double lat = 32.0853 + (Math.random() - 0.5) * 0.01; // Tel Aviv with small variation
        double lng = 34.7818 + (Math.random() - 0.5) * 0.01;
        
        Location currentLocation = new Location(lat, lng, "Current GPS Location");
        System.out.println("GPS location retrieved: " + currentLocation);
        return currentLocation;
    }

    /**
     * Get GPS signal strength
     */
    public int getSignalStrength() {
        int strength = 75 + (int)(Math.random() * 25); // 75-100%
        System.out.println("GPS signal strength: " + strength + "%");
        return strength;
    }

    public String getServiceId() { return serviceId; }
}

/**
 * ParentNotificationProxy class - Proxy for parent notifications
 */
class ParentNotificationProxy {
    private String proxyId;

    public ParentNotificationProxy(String proxyId) {
        this.proxyId = proxyId;
        System.out.println("ParentNotificationProxy " + proxyId + " initialized");
    }

    /**
     * Send SMS
     */
    public boolean sendSMS(String phoneNumber, String message) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
        // SMS sending simulation
        return Math.random() > 0.1; // 90% success rate
    }

    /**
     * Send push notification
     */
    public boolean sendPushNotification(String userId, String title, String message) {
        System.out.println("Sending push notification to user " + userId + " - " + title + ": " + message);
        return true;
    }

    /**
     * Send location update - step 17
     */
    public boolean sendLocationUpdate(String parentId, String childId, Location location, LocalDateTime timestamp) {
        System.out.println("Step 17: ParentNotificationProxy.sendLocationUpdate(" + 
                          parentId + ", " + childId + ", " + location + ", " + timestamp + ")");
        
        String message = "Location update: Child " + childId + " is at " + location.getDescription() + 
                        " at " + timestamp.toLocalTime();
        
        // Step 18: Send notification to parent
        pushNotification(parentId, message);
        
        return true;
    }

    /**
     * Send tracking confirmation - step 19
     */
    public boolean sendTrackingConfirmation(String parentId, String childId) {
        System.out.println("Step 19: ParentNotificationProxy.sendTrackingConfirmation(" + parentId + ", " + childId + ")");
        
        String message = "Active tracking confirmed for child " + childId;
        
        // Step 20: Display tracking status
        displayTrackingStatus(parentId, "Active tracking confirmed");
        
        return true;
    }

    /**
     * Push notification - step 18
     */
    public void pushNotification(String parentId, String message) {
        System.out.println("Step 18: ParentNotificationProxy → Parent.pushNotification(\"Child location updated\")");
        
        Parent parent = getParentById(parentId);
        parent.receiveNotification("Child location update", message);
    }

    /**
     * Display tracking status - step 20
     */
    public void displayTrackingStatus(String parentId, String status) {
        System.out.println("Step 20: ParentNotificationProxy → Parent.displayTrackingStatus(\"" + status + "\")");
        
        Parent parent = getParentById(parentId);
        parent.displayTrackingStatus(status);
    }

    /**
     * Get parent by ID
     */
    private Parent getParentById(String parentId) {
        return new Parent(parentId, "Parent_" + parentId);
    }

    public String getProxyId() { return proxyId; }
}
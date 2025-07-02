import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * LocationTracker class - location tracking
 */
public class LocationTracker {
    private String trackerId;
    private Location currentLocation;
    private String vehicleId;
    private boolean isTracking;
    private Map<String, TrackingInfo> trackedChildren;

    // Constructor
    public LocationTracker(String trackerId, String vehicleId) {
        this.trackerId = trackerId;
        this.vehicleId = vehicleId;
        this.isTracking = false;
        this.currentLocation = new Location(32.0853, 34.7818, "Tel Aviv Center"); // initial location
        this.trackedChildren = new HashMap<>();
        System.out.println("LocationTracker " + trackerId + " initialized for vehicle " + vehicleId);
    }

    /**
     * Start tracking - step 4
     */
    public void startTracking(String childId) {
        System.out.println("Step 4: LocationTracker.startTracking(" + childId + ")");
        
        // Step 5: Set active tracking mode
        setTrackingMode(TrackingMode.ACTIVE);
        
        TrackingInfo trackingInfo = new TrackingInfo(childId, LocalDateTime.now());
        trackedChildren.put(childId, trackingInfo);
        this.isTracking = true;
        
        System.out.println("Tracking started for child: " + childId);
    }

    /**
     * Set tracking mode - step 5
     */
    public void setTrackingMode(TrackingMode mode) {
        System.out.println("Step 5: LocationTracker.setTrackingMode(" + mode + ")");
        this.isTracking = (mode == TrackingMode.ACTIVE);
    }

    /**
     * Get current location - step 6
     */
    public Location getCurrentLocation() {
        // Step 6: Get location from GPS
        GPSServiceProxy gpsProxy = new GPSServiceProxy("GPS_001");
        System.out.println("Step 6: LocationTracker → GPSServiceProxy.getCurrentGPSLocation()");
        
        Location gpsLocation = gpsProxy.getCurrentGPSLocation();
        System.out.println("Step 7: GPSServiceProxy → LocationTracker.return currentLocation");
        
        this.currentLocation = gpsLocation;
        updateLocation();
        return this.currentLocation;
    }

    /**
     * Update location
     */
    public void updateLocation() {
        if (isTracking) {
            System.out.println("Location updated to: " + currentLocation);
            
            // Update location for all tracked children
            for (String childId : trackedChildren.keySet()) {
                updateChildLocation(childId, currentLocation);
            }
        }
    }

    /**
     * Update child location - step 8
     */
    private void updateChildLocation(String childId, Location location) {
        System.out.println("Step 8: LocationTracker → Child.updateCurrentLocation(" + location + ")");
        
        Child child = getChildById(childId);
        boolean locationUpdated = child.updateCurrentLocation(location);
        
        System.out.println("Step 9: Child → LocationTracker.return locationUpdated = " + locationUpdated);
        
        if (locationUpdated) {
            trackedChildren.get(childId).setLastLocationUpdate(LocalDateTime.now());
        }
    }

    /**
     * Estimate arrival time
     */
    public LocalDateTime estimateArrivalTime(String destination) {
        // Simulation of arrival time calculation
        LocalDateTime estimatedArrival = LocalDateTime.now().plusMinutes(15);
        System.out.println("Estimated arrival time to " + destination + ": " + estimatedArrival);
        return estimatedArrival;
    }

    /**
     * Capture current image - step 10
     */
    public Image captureCurrentImage(Camera camera) {
        System.out.println("Step 10: LocationTracker → Camera.captureCurrentImage()");
        return camera.captureCurrentImage();
    }

    /**
     * Confirm active tracking - step 16
     */
    public void confirmTrackingActive(String childId) {
        System.out.println("Step 16: LocationTracker.confirmTrackingActive(" + childId + ")");
        
        if (trackedChildren.containsKey(childId)) {
            trackedChildren.get(childId).setTrackingConfirmed(true);
            System.out.println("Tracking confirmed as active for child: " + childId);
        }
    }

    /**
     * Get child by ID
     */
    private Child getChildById(String childId) {
        // Simulation of getting child from database
        return new Child(childId, "Child_" + childId, 8, "SCHOOL_001", "PARENT_" + childId);
    }

    // Getters and Setters
    public String getTrackerId() { return trackerId; }
    public String getVehicleId() { return vehicleId; }
    public boolean isTracking() { return isTracking; }
    public Map<String, TrackingInfo> getTrackedChildren() { return trackedChildren; }
}

/**
 * Enum for tracking modes
 */
enum TrackingMode {
    ACTIVE,
    INACTIVE,
    STANDBY
}

/**
 * Tracking info class
 */
class TrackingInfo {
    private String childId;
    private LocalDateTime trackingStartTime;
    private LocalDateTime lastLocationUpdate;
    private boolean trackingConfirmed;

    public TrackingInfo(String childId, LocalDateTime startTime) {
        this.childId = childId;
        this.trackingStartTime = startTime;
        this.lastLocationUpdate = startTime;
        this.trackingConfirmed = false;
    }

    // Getters and Setters
    public String getChildId() { return childId; }
    public LocalDateTime getTrackingStartTime() { return trackingStartTime; }
    public LocalDateTime getLastLocationUpdate() { return lastLocationUpdate; }
    public void setLastLocationUpdate(LocalDateTime lastLocationUpdate) { 
        this.lastLocationUpdate = lastLocationUpdate; 
    }
    public boolean isTrackingConfirmed() { return trackingConfirmed; }
    public void setTrackingConfirmed(boolean trackingConfirmed) { 
        this.trackingConfirmed = trackingConfirmed; 
    }
}
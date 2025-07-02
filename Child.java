import java.time.LocalDateTime;

/**
 * Child class - represents a child in the transportation system
 */
public class Child {
    private String childId;
    private String name;
    private int age;
    private String schoolId;
    private String parentId;
    private FaceProfile faceProfile;
    private ChildStatus currentStatus;
    private LocalDateTime boardingTime;
    private Location currentLocation;

    // Constructor
    public Child(String childId, String name, int age, String schoolId, String parentId) {
        this.childId = childId;
        this.name = name;
        this.age = age;
        this.schoolId = schoolId;
        this.parentId = parentId;
        this.currentStatus = ChildStatus.NOT_ON_RIDE;
        this.faceProfile = new FaceProfile(childId);
    }

    /**
     * Child boards the ride
     */
    public boolean boardRide() {
        if (currentStatus == ChildStatus.NOT_ON_RIDE) {
            this.currentStatus = ChildStatus.ON_RIDE;
            this.boardingTime = LocalDateTime.now();
            System.out.println("Child " + name + " (ID: " + childId + ") boarded the ride");
            return true;
        }
        return false;
    }

    /**
     * Child exits the ride
     */
    public boolean exitRide() {
        if (currentStatus == ChildStatus.ON_RIDE) {
            this.currentStatus = ChildStatus.NOT_ON_RIDE;
            System.out.println("Child " + name + " (ID: " + childId + ") exited the ride");
            return true;
        }
        return false;
    }

    /**
     * Get current location
     */
    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    /**
     * Check if child is registered for ride
     */
    public boolean isRegisteredForRide() {
        return schoolId != null && !schoolId.isEmpty();
    }

    /**
     * Update child status
     */
    public void updateStatus(ChildStatus newStatus) {
        this.currentStatus = newStatus;
        System.out.println("Child " + name + " status updated to: " + newStatus);
    }

    /**
     * Get face profile
     */
    public FaceProfile getFaceProfile() {
        return this.faceProfile;
    }

    /**
     * Update current location
     */
    public boolean updateCurrentLocation(Location location) {
        this.currentLocation = location;
        System.out.println("Child " + name + " location updated to: " + location);
        return true;
    }

    /**
     * Verify child identity
     */
    public boolean verifyChildIdentity(String childId, Object image) {
        if (this.childId.equals(childId)) {
            System.out.println("Child identity confirmed for: " + name);
            return true;
        }
        return false;
    }

    /**
     * Check if child exists in system
     */
    public boolean verifyChildExists(String childId) {
        boolean exists = this.childId.equals(childId);
        System.out.println("Child exists verification for ID " + childId + ": " + exists);
        return exists;
    }

    // Getters
    public String getChildId() { return childId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSchoolId() { return schoolId; }
    public String getParentId() { return parentId; }
    public ChildStatus getCurrentStatus() { return currentStatus; }
    public LocalDateTime getBoardingTime() { return boardingTime; }
}

/**
 * Enum for child status
 */
enum ChildStatus {
    NOT_ON_RIDE,
    ON_RIDE,
    TRACKED_ACTIVE,
    CONFIRMED
}

/**
 * Face profile class
 */
class FaceProfile {
    private String profileId;
    private String data;

    public FaceProfile(String childId) {
        this.profileId = "FP_" + childId;
        this.data = "Face data for child " + childId;
    }

    public String getProfileId() { return profileId; }
    public String getData() { return data; }
}

/**
 * Location class
 */
class Location {
    private double latitude;
    private double longitude;
    private String description;

    public Location(double latitude, double longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Location{lat=" + latitude + ", lng=" + longitude + ", desc='" + description + "'}";
    }
}
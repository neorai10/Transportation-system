import java.time.LocalDateTime;

/**
 * Camera class - represents a camera in the transportation system
 */
public class Camera {
    private String cameraId;
    private String vehicleId;
    private String location;
    private boolean isActive;
    private String resolution;
    private LocalDateTime lastCaptureTime;

    // Constructor
    public Camera(String cameraId, String vehicleId, String location) {
        this.cameraId = cameraId;
        this.vehicleId = vehicleId;
        this.location = location;
        this.isActive = true;
        this.resolution = "1920x1080";
        this.lastCaptureTime = LocalDateTime.now();
    }

    /**
     * Capture image
     */
    public Image captureImage() {
        if (!isActive) {
            System.out.println("Camera " + cameraId + " is not active!");
            return null;
        }
        
        this.lastCaptureTime = LocalDateTime.now();
        Image image = new Image("IMG_" + cameraId + "_" + System.currentTimeMillis());
        System.out.println("Camera " + cameraId + " captured image: " + image.getImageId());
        return image;
    }

    /**
     * Capture current image
     */
    public Image captureCurrentImage() {
        System.out.println("Camera " + cameraId + " capturing current image...");
        return captureImage();
    }

    /**
     * Start recording
     */
    public void startRecording() {
        if (isActive) {
            System.out.println("Camera " + cameraId + " started recording");
        } else {
            System.out.println("Cannot start recording - Camera " + cameraId + " is not active");
        }
    }

    /**
     * Stop recording
     */
    public void stopRecording() {
        System.out.println("Camera " + cameraId + " stopped recording");
    }

    /**
     * Send image for recognition
     */
    public boolean sendImageToRecognition(FaceRecognitionAndControlSystem recognitionSystem) {
        if (!isActive) {
            return false;
        }
        
        Image image = captureImage();
        if (image != null) {
            recognitionSystem.processImage(image);
            System.out.println("Camera " + cameraId + " sent image to recognition system");
            return true;
        }
        return false;
    }

    /**
     * Calibrate the camera
     */
    public void calibrate() {
        System.out.println("Camera " + cameraId + " calibration completed");
        this.isActive = true;
    }

    /**
     * Get camera status
     */
    public CameraStatus getStatus() {
        return new CameraStatus(cameraId, isActive, resolution, lastCaptureTime);
    }

    // Getters and Setters
    public String getCameraId() { return cameraId; }
    public String getVehicleId() { return vehicleId; }
    public String getLocation() { return location; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }
    public String getResolution() { return resolution; }
    public LocalDateTime getLastCaptureTime() { return lastCaptureTime; }
}

/**
 * Image class
 */
class Image {
    private String imageId;
    private LocalDateTime timestamp;
    private String data;

    public Image(String imageId) {
        this.imageId = imageId;
        this.timestamp = LocalDateTime.now();
        this.data = "Image data for " + imageId;
    }

    public String getImageId() { return imageId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getData() { return data; }

    @Override
    public String toString() {
        return "Image{id='" + imageId + "', timestamp=" + timestamp + "}";
    }
}

/**
 * Camera status class
 */
class CameraStatus {
    private String cameraId;
    private boolean isActive;
    private String resolution;
    private LocalDateTime lastCaptureTime;

    public CameraStatus(String cameraId, boolean isActive, String resolution, LocalDateTime lastCaptureTime) {
        this.cameraId = cameraId;
        this.isActive = isActive;
        this.resolution = resolution;
        this.lastCaptureTime = lastCaptureTime;
    }

    public String getCameraId() { return cameraId; }
    public boolean isActive() { return isActive; }
    public String getResolution() { return resolution; }
    public LocalDateTime getLastCaptureTime() { return lastCaptureTime; }

    @Override
    public String toString() {
        return "CameraStatus{" +
                "cameraId='" + cameraId + '\'' +
                ", isActive=" + isActive +
                ", resolution='" + resolution + '\'' +
                ", lastCaptureTime=" + lastCaptureTime +
                '}';
    }
}
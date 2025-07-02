import java.time.LocalDateTime;

/**
 * SequenceDemo class - demonstrates the 20-step sequence diagram
 * This class implements the complete flow from parent request to tracking confirmation
 */
public class SequenceDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Starting 20-Step Sequence Diagram ===");
        System.out.println("Transportation System - Complete Flow Demonstration");
        System.out.println("=================================================\n");
        
        try {
            // Initialize all system components
            String childId = "CHILD_001";
            String parentId = "PARENT_001";
            String vehicleId = "VEHICLE_001";
            
            // Step 1: Parent requests child tracking
            System.out.println("=== STEP 1 ===");
            Parent parent = new Parent(parentId, "Sarah Cohen");
            ChildIdentificationManager identificationManager = new ChildIdentificationManager("MGR_001", vehicleId);
            parent.trackChild(childId, identificationManager);
            Thread.sleep(500);
            
            // Step 2: ChildIdentificationManager starts identification process
            System.out.println("\n=== STEP 2 ===");
            System.out.println("Step 2: ChildIdentificationManager.startIdentificationProcess()");
            identificationManager.startIdentificationProcess();
            Thread.sleep(500);
            
            // Step 3: Verify child exists in system
            System.out.println("\n=== STEP 3 ===");
            System.out.println("Step 3: ChildIdentificationManager.verifyChildExists(" + childId + ")");
            boolean childExists = identificationManager.verifyChildExists(childId);
            System.out.println("Child exists verification result: " + childExists);
            Thread.sleep(500);
            
            // Step 4-5: Start location tracking
            System.out.println("\n=== STEP 4-5 ===");
            LocationTracker locationTracker = new LocationTracker("LT_001", vehicleId);
            identificationManager.trackChild(childId, locationTracker);
            Thread.sleep(500);
            
            // Step 6-7: Get GPS location
            System.out.println("\n=== STEP 6-7 ===");
            Location currentLocation = locationTracker.getCurrentLocation();
            Thread.sleep(500);
            
            // Step 8-9: Update child location
            System.out.println("\n=== STEP 8-9 ===");
            // This is handled internally in locationTracker.getCurrentLocation()
            
            // Step 10: Capture current image
            System.out.println("\n=== STEP 10 ===");
            Camera camera = new Camera("CAM_001", vehicleId, "Front Door");
            Image capturedImage = locationTracker.captureCurrentImage(camera);
            System.out.println("Step 11: Camera → LocationTracker.return image = " + capturedImage.getImageId());
            Thread.sleep(500);
            
            // Step 12: Send image to face recognition
            System.out.println("\n=== STEP 12 ===");
            FaceRecognitionAndControlSystem recognitionSystem = new FaceRecognitionAndControlSystem("FRS_001", "OP_001");
            System.out.println("Step 12: LocationTracker → FaceRecognitionAndControlSystem.processImage(" + capturedImage.getImageId() + ")");
            String identifiedChildId = recognitionSystem.processImage(capturedImage);
            Thread.sleep(500);
            
            // Step 13: Return identification result
            System.out.println("\n=== STEP 13 ===");
            System.out.println("Step 13: FaceRecognitionAndControlSystem → LocationTracker.return identifiedChild = " + identifiedChildId);
            Thread.sleep(500);
            
            // Step 14: Report child status
            System.out.println("\n=== STEP 14 ===");
            Location childLocation = new Location(32.0853, 34.7818, "School Bus - Route 1");
            identificationManager.reportChildStatus(identifiedChildId, ChildStatus.TRACKED_ACTIVE, childLocation);
            Thread.sleep(500);
            
            // Step 15: Update child status (handled in reportChildStatus)
            System.out.println("\n=== STEP 15 ===");
            // Already executed in step 14
            
            // Step 16: Confirm tracking active
            System.out.println("\n=== STEP 16 ===");
            identificationManager.confirmTrackingActive(identifiedChildId, locationTracker);
            Thread.sleep(500);
            
            // Step 17: Send location update to parent
            System.out.println("\n=== STEP 17 ===");
            ParentNotificationProxy notificationProxy = new ParentNotificationProxy("PNP_001");
            identificationManager.sendLocationUpdate(identifiedChildId, childLocation, notificationProxy);
            Thread.sleep(500);
            
            // Step 18: Parent receives notification (handled in step 17)
            System.out.println("\n=== STEP 18 ===");
            // Already executed in step 17
            
            // Step 19: Send tracking confirmation
            System.out.println("\n=== STEP 19 ===");
            identificationManager.sendTrackingConfirmation(identifiedChildId, notificationProxy);
            Thread.sleep(500);
            
            // Step 20: Display tracking status (handled in step 19)
            System.out.println("\n=== STEP 20 ===");
            // Already executed in step 19
            
            // Final summary
            System.out.println("\n" + "=".repeat(50));
            System.out.println("🎉 SEQUENCE DIAGRAM COMPLETED SUCCESSFULLY! 🎉");
            System.out.println("=".repeat(50));
            System.out.println("✅ All 20 steps executed successfully");
            System.out.println("✅ Parent: " + parent.getName() + " (" + parentId + ")");
            System.out.println("✅ Child: " + identifiedChildId + " tracked successfully");
            System.out.println("✅ Location: " + childLocation.getDescription());
            System.out.println("✅ System: All components working properly");
            System.out.println("✅ Time: " + LocalDateTime.now());
            System.out.println("=".repeat(50));
            
        } catch (InterruptedException e) {
            System.err.println("Sequence execution interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during sequence execution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
/**
 * This file has a class for handling photos in the TinderApp system.
 * Stores and manages the name of the photo file for a user profile.
 * Author: Mauricio Cepeda Villanueva <amcepedav@udistrital.edu.co>
 * Author: Julian Carvajal Garnica <jcarvajalg@udistrital.edu.co>
 */
public class Photo {
    // Attribute to store the name of the photo file
    private String photoName; // Attribute to store the name of the photo file


public class Photo {

    /**
     * Attribute to store the name of the photo file.
     */
    private String photoName;

    /**
     * Constructs a Photo with the given file name.
     * @param photoName The name of the photo file.
     */
    public Photo(String photoName) {
        this.photoName = photoName;
    }

    /**
     * Gets the name of the photo file.
     * @return The name of the photo file.
     */
    public String getPhotoName() {
        return photoName;
    }

    /**
     * Sets the name of the photo file.
     * @param photoName The new name of the photo file.
     */
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
}
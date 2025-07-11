import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * User class
 * Represents a user in the TinderApp system.
 * Handles user data, registration, file persistence, notifications, and profile photo.
 * Author: Mauricio Cepeda Villanueva <amcepedav@udistrital.edu.co>
 * Author: Julian Carvajal Garnica <jcarvajalg@udistrital.edu.co>
 */
public class User {
    /**
     * Gets the user's name.
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }
    public String name;
    private String email;
    public LocalDate birthday;
    private String password;
    public String biography;
    public String interests;
    public String genderIdentity;
    public String sexualOrientation;
    public String photoPath;
    public Notification notification = new Notification();
    private static List<User> users = new ArrayList<>();
    private static final String FILE_PATH = "users.txt";

    /**
     * User class represents a user in the TinderApp system.
     * Handles user data, registration, file persistence, and photo path.
     */
    public User(String name, String email, String password, LocalDate birthday, String biography, String interests, String genderIdentity, String sexualOrientation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.biography = biography;
        this.interests = interests;
        this.genderIdentity = genderIdentity;
        this.sexualOrientation = sexualOrientation;
        this.photoPath = null;
        this.notification = new Notification();
    }

    // Constructor with notifications
    public User(String name, String email, String password, LocalDate birthday, String biography, String interests, String genderIdentity, String sexualOrientation, String photoPath, Notification notification) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.biography = biography;
        this.interests = interests;
        this.genderIdentity = genderIdentity;
        this.sexualOrientation = sexualOrientation;
        this.photoPath = photoPath;
        this.notification = notification != null ? notification : new Notification();
    }

    /**
     * Sets the user's profile photo path and updates the user in the list.
     * @param photoPath The path to the profile photo.
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        // Update the user in the list before saving
        for (User u : users) {
            if (u.getEmail().equals(this.email)) {
                u.photoPath = photoPath;
                break;
            }
        }
        saveToFile();
    }

    /**
     * Gets the user's email.
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's password.
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Registers the user if the email is not already in use. Updates data if user exists.
     * @return Success or error message.
     */
    public String register() {
        for (User user : users) {
            if (user.getEmail().equals(this.email)) {
                // Actualizar datos si el usuario ya existe
                user.name = this.name;
                user.password = this.password;
                user.birthday = this.birthday;
                user.biography = this.biography;
                user.interests = this.interests;
                user.genderIdentity = this.genderIdentity;
                user.sexualOrientation = this.sexualOrientation;
                user.photoPath = this.photoPath;
                saveToFile();
                return "Updated successfully.";
            }
        }
        users.add(this);
        saveToFile(); // Save the updated user list to the file
        return "Registration successful.";
    }

    /**
     * Gets the list of all registered users.
     * @return The list of all users.
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * Saves all users to a file. Writes each user's details in a structured format.
     * Uses '|' as a safe delimiter and replaces '|' in fields with \\|.
     */
    public static void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (User user : users) {
                String safeName = user.name.replace("|", "\\|");
                String safeEmail = user.email.replace("|", "\\|");
                String safePassword = user.password.replace("|", "\\|");
                String safeBirthday = user.birthday.toString().replace("|", "\\|");
                String safeBiography = (user.biography != null ? user.biography.replace("|", "\\|") : "");
                String safeInterests = (user.interests != null ? user.interests.replace("|", "\\|") : "");
                String safeGender = (user.genderIdentity != null ? user.genderIdentity.replace("|", "\\|") : "");
                String safeOrientation = (user.sexualOrientation != null ? user.sexualOrientation.replace("|", "\\|") : "");
                String safePhoto = (user.photoPath != null ? user.photoPath.replace("|", "\\|") : "");
                String notifStr = user.notification != null ? user.notification.serialize().replace("|", "\\|") : "";
                fileWriter.write(safeName + "|" + safeEmail + "|" + safePassword + "|" + safeBirthday + "|" +
                        safeBiography + "|" + safeInterests + "|" + safeGender + "|" + safeOrientation + "|" + safePhoto + "|" + notifStr + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    /**
     * Loads all users from a file. Reads each line and creates a User object for each entry.
     */
    public static void loadFromFile() {
        try {
            users.clear();
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Separate by '|'
                String[] parts = line.split("(?<!\\\\)\\|", -1);
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("\\|", "|");
                }
                if (parts.length >= 8) {
                    String name = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    LocalDate birthday = LocalDate.parse(parts[3]);
                    String biography = parts[4];
                    String interests = parts[5];
                    String genderIdentity = parts[6];
                    String sexualOrientation = parts[7];
                    String photoPath = parts.length >= 9 ? parts[8] : null;
                    Notification notif = (parts.length >= 10) ? new Notification(parts[9]) : new Notification();
                    User u = new User(name, email, password, birthday, biography, interests, genderIdentity, sexualOrientation, photoPath, notif);
                    users.add(u);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }

    /**
     * Adds a notification message to the user and saves to file.
     * @param msg The notification message.
     */
    public void addNotification(String msg) {
        if (notification == null) notification = new Notification();
        notification.addMessage(msg);
        saveToFile();
    }

    /**
     * Gets the list of notification messages for the user.
     * @return List of notification messages.
     */
    public List<String> getNotifications() {
        if (notification == null) return Collections.emptyList();
        return notification.getMessages();
    }
}
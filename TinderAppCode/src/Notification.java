import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 * This file has a class for handling notifications in the TinderApp system.
 * Displays notifications for likes received by the user.
 * Author: Mauricio Cepeda Villanueva <amcepedav@udistrital.edu.co>
 * Author: Julian Carvajal Garnica <jcarvajalg@udistrital.edu.co>
 */
public class Notification {

    /**
     * Shows the notifications frame for the given user email, reading likes.txt.
     * Displays up to 3 recent likes as notifications.
     * @param userEmail The email of the user to show notifications for.
     */
    public static void showNotificationsFrame(String userEmail) {
        java.util.List<String[]> likes = new java.util.ArrayList<>();
        try {
            String likesPath = System.getProperty("user.dir") + java.io.File.separator + "likes.txt";
            java.io.File likesFile = new java.io.File(likesPath);
            if (likesFile.exists()) {
                java.util.List<String> lines = java.nio.file.Files.readAllLines(likesFile.toPath());
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 4 && parts[0].trim().equalsIgnoreCase(userEmail.trim())) {
                        likes.add(parts);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Sort by timestamp descending
        likes.sort((a, b) -> Long.compare(Long.parseLong(b[3]), Long.parseLong(a[3])));

        JFrame notifFrame = new JFrame("Notifications");
        notifFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        notifFrame.setSize(420, 320);
        notifFrame.setLocationRelativeTo(null);
        notifFrame.setLayout(new java.awt.BorderLayout());

        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        panel.setBackground(java.awt.Color.WHITE);
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 24, 18, 24));

        // Main title
        javax.swing.JLabel title = new javax.swing.JLabel("New notifications");
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 26));
        title.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(javax.swing.Box.createVerticalStrut(8));

        // Subtitle in light gray
        javax.swing.JLabel subtitle = new javax.swing.JLabel("Will you have fans?");
        subtitle.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
        subtitle.setForeground(new java.awt.Color(140, 140, 140));
        subtitle.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        panel.add(subtitle);
        panel.add(javax.swing.Box.createVerticalStrut(18));

        if (likes.isEmpty()) {
            javax.swing.JLabel empty = new javax.swing.JLabel("No notifications.");
            empty.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
            empty.setForeground(new java.awt.Color(120,120,120));
            empty.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
            panel.add(empty);
        } else {
            int count = Math.min(3, likes.size());
            for (int i = 0; i < count; i++) {
                String[] like = likes.get(i);
                String senderName = like[2];
                String timestamp = like[3];
                String dateStr = "";
                try {
                    long ts = Long.parseLong(timestamp);
                    java.time.ZoneId zone = java.time.ZoneId.systemDefault();
                    java.time.LocalDate date = java.time.Instant.ofEpochMilli(ts).atZone(zone).toLocalDate();
                    java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dateStr = date.format(fmt);
                } catch (Exception ex) {
                    dateStr = timestamp;
                }
                // Panel to force real left alignment
                javax.swing.JPanel notifPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
                notifPanel.setOpaque(false);
                String html = String.format("<html><b>%s</b> <span style='color:#888'>gave you a like</span>, %s</html>",
                    senderName, dateStr);
                javax.swing.JLabel notif = new javax.swing.JLabel(html);
                notif.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
                notifPanel.add(notif);
                panel.add(notifPanel);
                panel.add(javax.swing.Box.createVerticalStrut(10));
            }
        }

        notifFrame.setContentPane(panel);
        notifFrame.setVisible(true);
    }
    private List<String> messages = new ArrayList<>();

    // Empty constructor
    public Notification() {}

    // Constructor from list
    public Notification(List<String> messages) {
        if (messages != null) this.messages = new ArrayList<>(messages);
    }

    // Constructor from serialized string
    public Notification(String serialized) {
        if (serialized != null && !serialized.isEmpty()) {
            String[] parts = serialized.split("~", -1);
            for (String msg : parts) {
                if (!msg.isEmpty()) messages.add(msg);
            }
        }
    }

    public void addMessage(String newMessage) {
        messages.add(newMessage);
    }

    public List<String> getMessages() {
        return messages;
    }

    // Serialize to string using ~ as separator
    public String serialize() {
        return String.join("~", messages);
    }
}

import javax.swing.*;
import java.awt.*;

/**
 * This file has the main application class for TinderApp.
 * Handles all UI frames, navigation, and main logic for registration, login, profile, and user browsing.
 * Author: Mauricio Cepeda Villanueva <amcepedav@udistrital.edu.co>
 * Author: Julian Carvajal Garnica <jcarvajalg@udistrital.edu.co>
 */
public class App {
    
    /**
     * Displays the registration frame for creating a new account frame 1.
     * Shows fields for name, email, password, and birthday.
     */
    public static void showRegisterFrame() {
        JFrame registerFrame = new JFrame("Create Account - TinderApp");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(420, 520);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 32, 16, 32));

        // Top panel with back arrow and title
        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new BoxLayout(arrowPanel, BoxLayout.X_AXIS));
        arrowPanel.setOpaque(false);
        arrowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        JButton btnBack = new JButton();
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setToolTipText("Back");
        btnBack.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 28));
        btnBack.setText("\u2B05");
        btnBack.setForeground(new Color(60, 60, 60));
        btnBack.setPreferredSize(new Dimension(44, 36));
        btnBack.setMaximumSize(new Dimension(44, 36));
        btnBack.addActionListener(e -> {
            registerFrame.dispose();
            showWelcomeScreen();
        });
        arrowPanel.add(btnBack);
        arrowPanel.add(Box.createHorizontalGlue());
        mainPanel.add(arrowPanel);
        mainPanel.add(Box.createVerticalStrut(2));

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(16));

        // Name
        JLabel labelName = new JLabel("Name:");
        labelName.setFont(new Font("Arial", Font.BOLD, 16));
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField fieldName = new JTextField();
        fieldName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelName);
        mainPanel.add(fieldName);

        // Email
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Arial", Font.BOLD, 16));
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField fieldEmail = new JTextField();
        fieldEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelEmail);
        mainPanel.add(fieldEmail);

        // Verify Email
        JLabel labelVerifyEmail = new JLabel("Verify Email:");
        labelVerifyEmail.setFont(new Font("Arial", Font.BOLD, 16));
        labelVerifyEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField fieldVerifyEmail = new JTextField();
        fieldVerifyEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelVerifyEmail);
        mainPanel.add(fieldVerifyEmail);

        // Password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Arial", Font.BOLD, 16));
        labelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField fieldPassword = new JPasswordField();
        fieldPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelPassword);
        mainPanel.add(fieldPassword);

        // Verify Password
        JLabel labelVerifyPassword = new JLabel("Verify Password:");
        labelVerifyPassword.setFont(new Font("Arial", Font.BOLD, 16));
        labelVerifyPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField fieldVerifyPassword = new JPasswordField();
        fieldVerifyPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelVerifyPassword);
        mainPanel.add(fieldVerifyPassword);

        // Birthday
        JLabel labelBirthday = new JLabel("Birthday (YYYY-MM-DD):");
        labelBirthday.setFont(new Font("Arial", Font.BOLD, 16));
        labelBirthday.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField fieldBirthday = new JTextField();
        fieldBirthday.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelBirthday);
        mainPanel.add(fieldBirthday);

        // Done button
        JButton btnDone = new JButton("Done!");
        btnDone.setFont(new Font("Arial", Font.BOLD, 18));
        btnDone.setBackground(Color.WHITE);
        btnDone.setForeground(Color.BLACK);
        btnDone.setFocusPainted(false);
        btnDone.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDone.setMaximumSize(new Dimension(200, 44));
        btnDone.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(Box.createVerticalStrut(18));
        mainPanel.add(btnDone);

        btnDone.addActionListener(e -> {
            String name = fieldName.getText().trim();
            String email = fieldEmail.getText().trim();
            String verifyEmail = fieldVerifyEmail.getText().trim();
            String password = new String(fieldPassword.getPassword());
            String verifyPassword = new String(fieldVerifyPassword.getPassword());
            String birthdayStr = fieldBirthday.getText().trim();
            if (!email.equals(verifyEmail)) {
                JOptionPane.showMessageDialog(registerFrame, "Emails do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(verifyPassword)) {
                JOptionPane.showMessageDialog(registerFrame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            java.time.LocalDate birthday;
            try {
                birthday = java.time.LocalDate.parse(birthdayStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(registerFrame, "Invalid date format. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            registerFrame.dispose();
            showRegisterFrameStep2(name, email, password, birthday);
        });

        registerFrame.setContentPane(mainPanel);
        registerFrame.setVisible(true);
    }

    /**
     * Registration frame 2: gender, orientation, interests, biography.
     */
    public static void showRegisterFrameStep2(String name, String email, String password, java.time.LocalDate birthday) {
        JFrame frame2 = new JFrame("Create Account");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setSize(400, 420);
        frame2.setLocationRelativeTo(null);
        frame2.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 30, 8, 30));

        // Top panel with back arrow and title
        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new BoxLayout(arrowPanel, BoxLayout.X_AXIS));
        arrowPanel.setOpaque(false);
        arrowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        JButton btnBack = new JButton();
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setToolTipText("Back");
        btnBack.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 28));
        btnBack.setText("\u2B05");
        btnBack.setForeground(new Color(60, 60, 60));
        btnBack.setPreferredSize(new Dimension(44, 36));
        btnBack.setMaximumSize(new Dimension(44, 36));
        btnBack.addActionListener(e -> {
            frame2.dispose();
            showRegisterFrame();
        });
        arrowPanel.add(btnBack);
        arrowPanel.add(Box.createHorizontalGlue());
        mainPanel.add(arrowPanel);
        mainPanel.add(Box.createVerticalStrut(2));

        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalStrut(12));

        // Gender Identity ComboBox
        JLabel genderLabel = new JLabel("Gender Identity:");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 15));
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] genderOptions = {"Cisgender men", "Cisgender woman", "Trans men", "Trans woman", "Non-Binary", "Other"};
        JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(genderLabel);
        mainPanel.add(genderCombo);
        mainPanel.add(Box.createVerticalStrut(10));

        // Sexual Orientation ComboBox
        JLabel orientationLabel = new JLabel("Sexual Orientation:");
        orientationLabel.setFont(new Font("Arial", Font.BOLD, 15));
        orientationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] orientationOptions = {"Heterosexual", "Homosexual", "Bisexual", "Other"};
        JComboBox<String> orientationCombo = new JComboBox<>(orientationOptions);
        orientationCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(orientationLabel);
        mainPanel.add(orientationCombo);
        mainPanel.add(Box.createVerticalStrut(10));

        // Interests ComboBox
        JLabel interestsLabel = new JLabel("Interests:");
        interestsLabel.setFont(new Font("Arial", Font.BOLD, 15));
        interestsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] interestsOptions = {"Cisgender Men", "Cisgender Women", "Trans Men", "Trans Women", "Non-Binary", "Other"};
        JComboBox<String> interestsCombo = new JComboBox<>(interestsOptions);
        interestsCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(interestsLabel);
        mainPanel.add(interestsCombo);
        mainPanel.add(Box.createVerticalStrut(10));

        // Biography field
        JLabel bioLabel = new JLabel("Biography:");
        bioLabel.setFont(new Font("Arial", Font.BOLD, 15));
        bioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea bioArea = new JTextArea(3, 20);
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        bioArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane bioScroll = new JScrollPane(bioArea);
        bioScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        mainPanel.add(bioLabel);
        mainPanel.add(bioScroll);
        mainPanel.add(Box.createVerticalStrut(16));

        // Done button
        JButton btnDone = new JButton("Done!");
        btnDone.setFont(new Font("Arial", Font.BOLD, 16));
        btnDone.setBackground(Color.WHITE);
        btnDone.setForeground(Color.BLACK);
        btnDone.setFocusPainted(false);
        btnDone.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDone.setMaximumSize(new Dimension(200, 44));
        btnDone.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(btnDone);
        mainPanel.add(Box.createVerticalGlue());

        btnDone.addActionListener(e -> {
            String genderIdentity = (String) genderCombo.getSelectedItem();
            String sexualOrientation = (String) orientationCombo.getSelectedItem();
            String interests = (String) interestsCombo.getSelectedItem();
            String biography = bioArea.getText().trim();
            User user = new User(name, email, password, birthday, biography, interests, genderIdentity, sexualOrientation);
            frame2.dispose();
            showProfilePhotoFrame(user);
        });

        frame2.setContentPane(mainPanel);
        frame2.setVisible(true);
    }

    /**
     * Shows the frame to select the profile photo after registration.
     * This screen allows the user to select and preview a profile photo after creating an account.
     * The selected photo can be saved and associated with the user.
     */
    public static void showProfilePhotoFrame(User user) {
        JFrame photoFrame = new JFrame("Add profile photo");
        photoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        photoFrame.setSize(420, 480);
        photoFrame.setLocationRelativeTo(null);
        photoFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel with back arrow and title
        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new BoxLayout(arrowPanel, BoxLayout.X_AXIS));
        arrowPanel.setOpaque(false);
        arrowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        JButton btnBack = new JButton();
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setToolTipText("Back");
        btnBack.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 28));
        btnBack.setText("\u2B05");
        btnBack.setForeground(new Color(60, 60, 60));
        btnBack.setPreferredSize(new Dimension(44, 36));
        btnBack.setMaximumSize(new Dimension(44, 36));
        btnBack.addActionListener(e -> {
            photoFrame.dispose();
            showWelcomeScreen();
        });
        arrowPanel.add(btnBack);
        arrowPanel.add(Box.createHorizontalGlue());
        mainPanel.add(arrowPanel);
        mainPanel.add(Box.createVerticalStrut(2));

        JLabel label = new JLabel("Add a photo to your profile");
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalStrut(18));

        // Photo preview box
        JLabel photoLabel = new JLabel();
        photoLabel.setPreferredSize(new Dimension(220, 220));
        photoLabel.setMaximumSize(new Dimension(220, 220));
        photoLabel.setMinimumSize(new Dimension(220, 220));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        photoLabel.setOpaque(true);
        photoLabel.setBackground(new Color(240,240,240));
        photoLabel.setText("+");
        photoLabel.setFont(new Font("Arial", Font.BOLD, 90));
        photoLabel.setForeground(Color.GRAY);
        photoLabel.setHorizontalTextPosition(JLabel.CENTER);
        photoLabel.setVerticalTextPosition(JLabel.CENTER);
        photoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(photoLabel);
        mainPanel.add(Box.createVerticalStrut(18));

        // Button to select photo
        JButton btnSelect = new JButton("Select photo");
        btnSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(btnSelect);
        mainPanel.add(Box.createVerticalStrut(18));

        // Done button
        JButton btnDone = new JButton("Done!");
        btnDone.setFont(new Font("Arial", Font.BOLD, 16));
        btnDone.setBackground(Color.WHITE);
        btnDone.setForeground(Color.BLACK);
        btnDone.setFocusPainted(false);
        btnDone.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDone.setMaximumSize(new Dimension(200, 44));
        btnDone.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(btnDone);

        final String[] photoPath = {null};

        btnSelect.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select profile photo");
            int result = fileChooser.showOpenDialog(photoFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                photoPath[0] = selectedFile.getAbsolutePath();
                ImageIcon icon = new ImageIcon(new ImageIcon(photoPath[0]).getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH));
                photoLabel.setIcon(icon);
                photoLabel.setText("");
            }
        });

        btnDone.addActionListener(e -> {
            if (photoPath[0] != null) {
                user.setPhotoPath(photoPath[0]);
            }
            user.register();
            JOptionPane.showMessageDialog(photoFrame, "Profile created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            photoFrame.dispose();
            showWelcomeScreen();
        });
        photoFrame.setContentPane(mainPanel);
        photoFrame.setVisible(true);
    }

    /**
     * Displays the user's profile frame, allowing you to view their bio, interests, and profile picture, as well as access notifications.
     */
    public static void showMyProfileFrame(User user) {
        JFrame profileFrame = new JFrame("My Profile - TinderApp");
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setSize(400, 520);
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        // Top panel with back arrow
        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new BoxLayout(arrowPanel, BoxLayout.X_AXIS));
        arrowPanel.setOpaque(false);
        arrowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        JButton btnBack = new JButton();
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setToolTipText("Back");
        btnBack.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 28));
        btnBack.setText("\u2B05");
        btnBack.setForeground(new Color(60, 60, 60));
        btnBack.setPreferredSize(new Dimension(44, 36));
        btnBack.setMaximumSize(new Dimension(44, 36));
        btnBack.addActionListener(e -> {
            profileFrame.dispose();
            showBrowseUsersFrame(user);
        });
        arrowPanel.add(btnBack);
        arrowPanel.add(Box.createHorizontalGlue());
        mainPanel.add(arrowPanel);
        mainPanel.add(Box.createVerticalStrut(2));

        JLabel title = new JLabel("My Profile");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(8));

        // Profile photo panel
        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.Y_AXIS));
        photoPanel.setOpaque(false);
        photoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel photoLabel = new JLabel();
        photoLabel.setPreferredSize(new Dimension(140, 140));
        photoLabel.setMaximumSize(new Dimension(140, 140));
        photoLabel.setMinimumSize(new Dimension(140, 140));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        photoLabel.setOpaque(true);
        photoLabel.setBackground(new Color(240,240,240));
        photoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (user.photoPath != null && !user.photoPath.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(new ImageIcon(user.photoPath).getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH));
                photoLabel.setIcon(icon);
                photoLabel.setText("");
            } catch (Exception ex) {
                photoLabel.setIcon(null);
                photoLabel.setText("No photo");
            }
        } else {
            photoLabel.setIcon(null);
            photoLabel.setText("No photo");
        }
        photoPanel.add(photoLabel);
        photoPanel.add(Box.createVerticalStrut(8));
        JLabel nameLabel = new JLabel(user.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        photoPanel.add(nameLabel);
        mainPanel.add(photoPanel);
        mainPanel.add(Box.createVerticalStrut(8));

        // Biography
        JLabel bioTitle = new JLabel("Biography:");
        bioTitle.setFont(new Font("Arial", Font.BOLD, 15));
        bioTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(bioTitle);
        JTextArea bioArea = new JTextArea(user.biography, 5, 20);
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        bioArea.setFont(new Font("Arial", Font.PLAIN, 14));
        bioArea.setEditable(false);
        bioArea.setBackground(new Color(245,245,245));
        JScrollPane bioScroll = new JScrollPane(bioArea);
        bioScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        bioScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        mainPanel.add(bioScroll);
        mainPanel.add(Box.createVerticalStrut(8));

        // Interests
        JLabel interestsTitle = new JLabel("Interests:");
        interestsTitle.setFont(new Font("Arial", Font.BOLD, 15));
        interestsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(interestsTitle);
        JLabel interestsLabel = new JLabel(user.interests);
        interestsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        interestsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(interestsLabel);
        mainPanel.add(Box.createVerticalStrut(12));

        // Notifications button
        JButton btnNotifications = new JButton("Notifications");
        btnNotifications.setFont(new Font("Arial", Font.BOLD, 13));
        btnNotifications.setBackground(new Color(230, 230, 230));
        btnNotifications.setForeground(new Color(60, 60, 60));
        btnNotifications.setFocusPainted(false);
        btnNotifications.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNotifications.addActionListener(e -> {
            Notification.showNotificationsFrame(user.getEmail());
        });
        mainPanel.add(btnNotifications);
        profileFrame.add(mainPanel, BorderLayout.CENTER);
        profileFrame.setVisible(true);
    }

    /**
     * Main Tinder App: user browser frame (like/dislike).
     */
    public static void showBrowseUsersFrame(User loggedInUser) {
        JFrame frame = new JFrame("Tinder App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 650);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel title = new JLabel("Tinder App");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(10));

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameLabel);

        JTextArea bioArea = new JTextArea();
        bioArea.setFont(new Font("Arial", Font.ITALIC, 16));
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        bioArea.setEditable(false);
        bioArea.setOpaque(false);
        bioArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        bioArea.setMaximumSize(new Dimension(400, 60));
        // Centrar el texto de la biografía
        bioArea.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
        bioArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        bioArea.setHighlighter(null);
        javax.swing.text.SimpleAttributeSet center = new javax.swing.text.SimpleAttributeSet();
        javax.swing.text.StyleConstants.setAlignment(center, javax.swing.text.StyleConstants.ALIGN_CENTER);
        javax.swing.text.StyledDocument doc = new javax.swing.text.DefaultStyledDocument();
        javax.swing.JTextPane bioPane = new javax.swing.JTextPane(doc);
        bioPane.setFont(new Font("Arial", Font.ITALIC, 16));
        bioPane.setEditable(false);
        bioPane.setOpaque(false);
        bioPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        bioPane.setMaximumSize(new Dimension(400, 60));
        try {
            doc.insertString(0, bioArea.getText(), null);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        } catch (Exception ex) {}
        JScrollPane bioScroll = new JScrollPane(bioPane);
        bioScroll.setBorder(null);
        bioScroll.setOpaque(false);
        bioScroll.getViewport().setOpaque(false);
        bioScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        bioScroll.setMaximumSize(new Dimension(400, 60));
        mainPanel.add(bioScroll);

        // Message like/dislike
        JLabel likeStatusLabel = new JLabel("");
        likeStatusLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        likeStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        likeStatusLabel.setForeground(new Color(100, 100, 100));
        mainPanel.add(Box.createVerticalStrut(4));
        mainPanel.add(likeStatusLabel);

        JLabel interestsLabel = new JLabel();
        interestsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        interestsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(interestsLabel);

        JLabel genderLabel = new JLabel();
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(genderLabel);

        JLabel orientationLabel = new JLabel();
        orientationLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        orientationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(orientationLabel);

        JLabel birthdayLabel = new JLabel();
        birthdayLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        birthdayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(birthdayLabel);

        JLabel photoLabel = new JLabel();
        photoLabel.setPreferredSize(new Dimension(260, 260));
        photoLabel.setMaximumSize(new Dimension(260, 260));
        photoLabel.setMinimumSize(new Dimension(260, 260));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        photoLabel.setOpaque(true);
        photoLabel.setBackground(new Color(240,240,240));
        photoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.X_AXIS));
        photoPanel.setOpaque(false);
        photoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        photoPanel.add(photoLabel);
        photoPanel.add(Box.createHorizontalStrut(10));
        JButton btnNext = new JButton("Next");
        btnNext.setFont(new Font("Arial", Font.PLAIN, 13));
        btnNext.setBackground(new Color(230,230,230));
        btnNext.setFocusPainted(false);
        btnNext.setMaximumSize(new Dimension(60, 32));
        photoPanel.add(btnNext);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(photoPanel);
        mainPanel.add(Box.createVerticalStrut(18));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setOpaque(false);
        JButton btnLogout = new JButton("Log out");
        JButton btnLike = new JButton("Like");
        btnLike.setBackground(new Color(0, 200, 0)); // Verde
        btnLike.setForeground(Color.WHITE);
        btnLike.setFocusPainted(false);
        btnLike.setFont(new Font("Arial", Font.BOLD, 15));
        JButton btnDislike = new JButton("Dislike");
        btnDislike.setBackground(new Color(220, 0, 0)); // Rojo
        btnDislike.setForeground(Color.WHITE);
        btnDislike.setFocusPainted(false);
        btnDislike.setFont(new Font("Arial", Font.BOLD, 15));
        JButton btnProfile = new JButton("My Profile");
        btnPanel.add(btnLogout);
        btnPanel.add(Box.createHorizontalStrut(12));
        btnPanel.add(btnLike);
        btnPanel.add(Box.createHorizontalStrut(12));
        btnPanel.add(btnDislike);
        btnPanel.add(Box.createHorizontalStrut(12));
        btnPanel.add(btnProfile);
        mainPanel.add(btnPanel);

        java.util.List<User> users = new java.util.ArrayList<>();
        for (User u : User.getUsers()) {
            if (!u.getEmail().equals(loggedInUser.getEmail())) {
                users.add(u);
            }
        }
        java.util.Collections.shuffle(users); // Mostrar en orden aleatorio
        final int[] index = {0};

        Runnable updateUserCard = () -> {
            if (users.size() == 0) {
                nameLabel.setText("No users to show.");
                bioArea.setText("");
                interestsLabel.setText("");
                genderLabel.setText("");
                orientationLabel.setText("");
                birthdayLabel.setText("");
                photoLabel.setIcon(null);
                photoLabel.setText("No photo");
                btnLike.setEnabled(false);
                btnDislike.setEnabled(false);
                btnProfile.setEnabled(false);
                return;
            }
            User u = users.get(index[0]);
            nameLabel.setText(u.getName());
            String bioText = '"' + (u.biography != null ? u.biography : "") + '"';
            try {
                javax.swing.text.StyledDocument doc2 = (javax.swing.text.StyledDocument) bioPane.getDocument();
                doc2.remove(0, doc2.getLength());
                doc2.insertString(0, bioText, null);
                javax.swing.text.SimpleAttributeSet center2 = new javax.swing.text.SimpleAttributeSet();
                javax.swing.text.StyleConstants.setAlignment(center2, javax.swing.text.StyleConstants.ALIGN_CENTER);
                doc2.setParagraphAttributes(0, doc2.getLength(), center2, false);
            } catch (Exception ex) {}
            likeStatusLabel.setText("");
            interestsLabel.setText("Interests: " + (u.interests != null ? u.interests : ""));
            genderLabel.setText("Gender Identity: " + (u.genderIdentity != null ? u.genderIdentity : ""));
            orientationLabel.setText("Sexual Orientation: " + (u.sexualOrientation != null ? u.sexualOrientation : ""));
            // Perform calculations to convert date of birth to age
            String ageStr = "";
            if (u.birthday != null) {
                java.time.LocalDate today = java.time.LocalDate.now();
                java.time.Period period = java.time.Period.between(u.birthday, today);
                ageStr = period.getYears() + " Years old";
            }
            birthdayLabel.setText(ageStr);
            if (u.photoPath != null && !u.photoPath.isEmpty()) {
                try {
                    ImageIcon icon = new ImageIcon(new ImageIcon(u.photoPath).getImage().getScaledInstance(260, 260, Image.SCALE_SMOOTH));
                    photoLabel.setIcon(icon);
                    photoLabel.setText("");
                } catch (Exception ex) {
                    photoLabel.setIcon(null);
                    photoLabel.setText("No photo");
                }
            } else {
                photoLabel.setIcon(null);
                photoLabel.setText("No photo");
            }
        };
        updateUserCard.run();

        btnLike.addActionListener(e -> {
            if (users.size() > 0) {
                User u = users.get(index[0]);
                likeStatusLabel.setText("You liked this user");
                // Save like to likes.txt only if it doesn't already exist
                try {
                    java.io.File likesFile = new java.io.File("likes.txt");
                    java.util.List<String> lines = new java.util.ArrayList<>();
                    if (likesFile.exists()) {
                        java.nio.file.Path path = likesFile.toPath();
                        lines = java.nio.file.Files.readAllLines(path);
                    }
                    String receptor = u.getEmail();
                    String remitente = loggedInUser.getEmail();
                    String nombreRemitente = loggedInUser.getName();
                    String likeKey = receptor + "|" + remitente + "|";
                    boolean alreadyLiked = false;
                    for (String line : lines) {
                        if (line.startsWith(likeKey)) {
                            alreadyLiked = true;
                            break;
                        }
                    }
                    if (!alreadyLiked) {
                        String timestamp = String.valueOf(System.currentTimeMillis());
                        String newLine = receptor + "|" + remitente + "|" + nombreRemitente + "|" + timestamp;
                        lines.add(newLine);
                        java.nio.file.Files.write(likesFile.toPath(), lines);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnDislike.addActionListener(e -> {
            if (users.size() > 0) {
                User u = users.get(index[0]);
                likeStatusLabel.setText("You disliked this user");
                // Don't save dislike notification 
            }
        });
        btnNext.addActionListener(e -> {
            if (users.size() > 0) {
                index[0] = (index[0] + 1) % users.size();
                updateUserCard.run();
            }
        });
        btnLogout.addActionListener(e -> {
            frame.dispose();
            showWelcomeScreen();
        });
        btnProfile.addActionListener(e -> {
            frame.dispose();
            showMyProfileFrame(loggedInUser);
        });

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    /**
     * Displays the welcome screen with login and register buttons.
     * This is the entry point for the GUI. It shows the app name and two main buttons:
     * "Log In" and "Create account". Each button navigates to its respective screen.
     */
    public static void showWelcomeScreen() {
        // Main window (JFrame) configuration
        JFrame frame = new JFrame("Tinder App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Main panel with vertical BoxLayout to stack elements
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Top spacing to separate from the edge
        mainPanel.add(Box.createVerticalStrut(36));

        // Panel for welcome texts
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        // Label for "Welcome to"
        JLabel labelWelcome = new JLabel("Welcome to");
        labelWelcome.setFont(new Font("Arial", Font.PLAIN, 15));
        labelWelcome.setForeground(Color.BLACK);
        int leftMargin = 40; // Move text to right
        labelWelcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelWelcome.setBorder(BorderFactory.createEmptyBorder(0, leftMargin, 0, 0));
        textPanel.add(labelWelcome);

        // Label for "TinderApp"
        JLabel labelTinder = new JLabel("TinderApp");
        labelTinder.setFont(new Font("Arial", Font.BOLD, 36));
        labelTinder.setForeground(Color.BLACK);
        labelTinder.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(labelTinder);

        mainPanel.add(textPanel);

        // Spacing between buttons and texts
        mainPanel.add(Box.createVerticalStrut(32));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // Button for Log In
        JButton btnLogin = new JButton("Log In");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(200, 44));
        btnLogin.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 2, true));

        // Button for Create Account
        JButton btnRegister = new JButton("Create account");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.setFocusPainted(false);
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setMaximumSize(new Dimension(200, 44));
        btnRegister.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 2, true));

        // Add buttons to the panel
        buttonPanel.add(btnLogin);
        buttonPanel.add(Box.createVerticalStrut(18));
        buttonPanel.add(btnRegister);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        frame.setContentPane(mainPanel);

        // Button actions: navigate to login or registration
        btnLogin.addActionListener(e -> {
            frame.dispose();
            showLoginFrame();
        });

        btnRegister.addActionListener(e -> {
            frame.dispose();
            showRegisterFrame();
        });

        // Show the welcome screen
        frame.setVisible(true);
    }

    /**
     * Displays the login frame for user authentication.
     * This method shows a form for the user to enter their email and password.
     * If authentication is successful, a success message is shown and the user is redirected.
     */
    public static void showLoginFrame() {
        // Login window (Log In)
        JFrame loginFrame = new JFrame("Log In - TinderApp");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        // Less space at the top and bottom to move content up
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 30, 8, 30));

        // Top panel with back arrow and centered title, visually attractive
        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new BoxLayout(arrowPanel, BoxLayout.X_AXIS));
        arrowPanel.setOpaque(false);
        arrowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        JButton btnBack = new JButton();
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setToolTipText("Back");
        // Use a stylized Unicode arrow icon
        btnBack.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 28));
        btnBack.setText("\u2B05"); // Flecha estilizada
        btnBack.setForeground(new Color(60, 60, 60));
        btnBack.setPreferredSize(new Dimension(44, 36));
        btnBack.setMaximumSize(new Dimension(44, 36));
        btnBack.addActionListener(e -> {
            loginFrame.dispose();
            showWelcomeScreen();
        });
        arrowPanel.add(btnBack);
        arrowPanel.add(Box.createHorizontalGlue());
        mainPanel.add(arrowPanel);
        mainPanel.add(Box.createVerticalStrut(2));

        // Centered title
        JLabel title = new JLabel("Log In");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.BLACK);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setOpaque(false);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalGlue());
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(8));

        // Centered Email field
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Arial", Font.BOLD, 15));
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField fieldEmail = new JTextField();
        fieldEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelEmail);
        mainPanel.add(fieldEmail);

        // Centered Password field
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Arial", Font.BOLD, 15));
        labelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField fieldPassword = new JPasswordField();
        fieldPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        mainPanel.add(labelPassword);
        mainPanel.add(fieldPassword);

        // Centered button with text "Done!"
        JButton btnSubmit = new JButton("Done!");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
        btnSubmit.setBackground(Color.WHITE);
        btnSubmit.setForeground(Color.BLACK);
        btnSubmit.setFocusPainted(false);
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setOpaque(false);
        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(btnSubmit);
        btnPanel.add(Box.createHorizontalGlue());
        btnSubmit.setMaximumSize(new Dimension(200, 44));
        btnSubmit.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(Box.createVerticalStrut(18));
        mainPanel.add(btnPanel);

        loginFrame.setContentPane(mainPanel);

        btnSubmit.addActionListener(e -> {
            // Real authentication logic
            String email = fieldEmail.getText().trim();
            String password = new String(fieldPassword.getPassword());
            User loggedInUser = null;
            for (User user : User.getUsers()) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    loggedInUser = user;
                    break;
                }
            }
            if (loggedInUser != null) {
                JOptionPane.showMessageDialog(loginFrame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loginFrame.dispose();
                showBrowseUsersFrame(loggedInUser);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid email or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginFrame.setVisible(true);
    }

    /**
     * Prints the main menu options for the user in the console.
     */
    public static void printMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    /**
     * Entry point of the application.
     * 
     */
    public static void main(String[] args) {
        User.loadFromFile();
        SwingUtilities.invokeLater(() -> showWelcomeScreen());
    }
}
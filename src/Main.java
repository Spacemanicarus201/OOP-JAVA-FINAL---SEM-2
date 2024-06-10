import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

abstract class Staff extends Person {
    protected List<Horse> assignedHorses;
    protected String specialty;

    public Staff(String name, String specialty) {
        super(name);
        this.assignedHorses = new ArrayList<>();
        this.specialty = specialty;
    }

    public List<Horse> getAssignedHorses() {
        return assignedHorses;
    }

    public void assignHorse(Horse horse) {
        assignedHorses.add(horse);
        horse.setAssignedStaff(this);
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public abstract void performDuties();
}

class Trainer extends Staff {
    private int trainingHours;

    public Trainer(String name, String specialty, int trainingHours) {
        super(name, specialty);
        this.trainingHours = trainingHours;
    }

    public int getTrainingHours() {
        return trainingHours;
    }

    public void setTrainingHours(int trainingHours) {
        this.trainingHours = trainingHours;
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is training horses for " + trainingHours + " hours.");
        for (Horse horse : assignedHorses) {
            System.out.println(name + " has performed training on " + horse.getName());
        }
    }
}

class Veterinarian extends Staff {
    public Veterinarian(String name, String specialty) {
        super(name, specialty);
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is performing veterinary duties in " + specialty + ".");
        for (Horse horse : assignedHorses) {
            System.out.println(name + " has performed veterinary duties on " + horse.getName());
        }
    }
}

class Groomer extends Staff {
    private int age;

    public Groomer(String name, int age, String specialty) {
        super(name, specialty);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is grooming horses.");
        for (Horse horse : assignedHorses) {
            System.out.println(name + " has groomed " + horse.getName());
        }
    }
}

class Ferrier extends Staff {
    private boolean hasCertification;

    public Ferrier(String name, boolean hasCertification) {
        super(name, "Ferrier");
        this.hasCertification = hasCertification;
    }

    public boolean isCertified() {
        return hasCertification;
    }

    public void setCertification(boolean hasCertification) {
        this.hasCertification = hasCertification;
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is performing ferrier duties" + (hasCertification ? " with certification." : "."));
        for (Horse horse : assignedHorses) {
            System.out.println(name + " has performed ferrier duties on " + horse.getName());
        }
    }
}

class Horse {
    private String name;
    private String breed;
    private int age;
    private String gender;
    private String specialty;
    private Staff assignedStaff;

    public Horse(String name, String breed, int age, String gender, String specialty) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Staff getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(Staff assignedStaff) {
        this.assignedStaff = assignedStaff;
    }
}

class HorseManagementSystem {
    private List<Staff> staffList;
    private List<Horse> horseList;

    public HorseManagementSystem() {
        this.staffList = new ArrayList<>();
        this.horseList = new ArrayList<>();
    }

    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public void addHorse(Horse horse) {
        horseList.add(horse);
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public List<Horse> getHorseList() {
        return horseList;
    }

    public Staff findStaffByName(String name) {
        for (Staff staff : staffList) {
            if (staff.getName().equals(name)) {
                return staff;
            }
        }
        return null;
    }

    public Horse findHorseByName(String name) {
        for (Horse horse : horseList) {
            if (horse.getName().equals(name)) {
                return horse;
            }
        }
        return null;
    }
}

public class Main {
    private static HorseManagementSystem hms = new HorseManagementSystem();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Horse Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1));

        JButton addSubjectsButton = new JButton("Add Subjects");
        JButton viewAllButton = new JButton("View All");
        JButton assignHorseButton = new JButton("Assign Horse to Staff");
        JButton performDutiesButton = new JButton("Perform Duties");

        addSubjectsButton.setPreferredSize(new Dimension(200, 40));
        viewAllButton.setPreferredSize(new Dimension(200, 40));
        assignHorseButton.setPreferredSize(new Dimension(200, 40));
        performDutiesButton.setPreferredSize(new Dimension(200, 40));

        addSubjectsButton.addActionListener(e -> showAddSubjectsPanel(frame));
        viewAllButton.addActionListener(e -> showViewAllPanel(frame));
        assignHorseButton.addActionListener(e -> assignHorseToStaff(frame));
        performDutiesButton.addActionListener(e -> showDutiesPanel(frame));

        menuPanel.add(addSubjectsButton);
        menuPanel.add(viewAllButton);
        menuPanel.add(assignHorseButton);
        menuPanel.add(performDutiesButton);

        frame.add(menuPanel, BorderLayout.WEST);
        frame.setVisible(true);
    }

    private static void showAddSubjectsPanel(JFrame frame) {
        JFrame subjectsFrame = new JFrame("Add Subjects");
        subjectsFrame.setSize(400, 200);
        subjectsFrame.setLayout(new GridLayout(2, 1));

        JButton addStaffButton = new JButton("Add Staff");
        JButton addHorseButton = new JButton("Add Horse");

        addStaffButton.setPreferredSize(new Dimension(200, 40));
        addHorseButton.setPreferredSize(new Dimension(200, 40));

        addStaffButton.addActionListener(e -> showAddStaffPanel(frame));
        addHorseButton.addActionListener(e -> showAddHorsePanel(frame));

        subjectsFrame.add(addStaffButton);
        subjectsFrame.add(addHorseButton);

        subjectsFrame.setVisible(true);
    }

    private static void showAddStaffPanel(JFrame frame) {
        JFrame staffFrame = new JFrame("Add Staff");
        staffFrame.setSize(400, 300);
        staffFrame.setLayout(new GridLayout(8, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        String[] staffTypes = {"Trainer", "Veterinarian", "Groomer", "Ferrier"};
        JComboBox<String> typeComboBox = new JComboBox<>(staffTypes);
        JLabel hoursLabel = new JLabel("Training Hours:");
        JTextField hoursField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel certificationLabel = new JLabel("Certified:");
        JCheckBox certificationBox = new JCheckBox();
        JLabel specialtyLabel = new JLabel("Specialty:");
        String[] specialties = {"Dressage", "Jumping", "Eventing"};
        JComboBox<String> specialtyComboBox = new JComboBox<>(specialties);

        hoursLabel.setVisible(false);
        hoursField.setVisible(false);
        ageLabel.setVisible(false);
        ageField.setVisible(false);
        certificationLabel.setVisible(false);
        certificationBox.setVisible(false);

        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            hoursLabel.setVisible(false);
            hoursField.setVisible(false);
            ageLabel.setVisible(false);
            ageField.setVisible(false);
            certificationLabel.setVisible(false);
            certificationBox.setVisible(false);

            switch (selectedType) {
                case "Trainer":
                    hoursLabel.setVisible(true);
                    hoursField.setVisible(true);
                    break;
                case "Groomer":
                    ageLabel.setVisible(true);
                    ageField.setVisible(true);
                    break;
                case "Ferrier":
                    certificationLabel.setVisible(true);
                    certificationBox.setVisible(true);
                    break;
            }
        });

        JButton submitButton = new JButton("Add Staff");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String selectedType = (String) typeComboBox.getSelectedItem();
            String specialty = (String) specialtyComboBox.getSelectedItem();

            Staff staff = null;
            switch (selectedType) {
                case "Trainer":
                    int trainingHours = Integer.parseInt(hoursField.getText());
                    staff = new Trainer(name, specialty, trainingHours);
                    break;
                case "Veterinarian":
                    staff = new Veterinarian(name, specialty);
                    break;
                case "Groomer":
                    int age = Integer.parseInt(ageField.getText());
                    staff = new Groomer(name, age, specialty);
                    break;
                case "Ferrier":
                    boolean hasCertification = certificationBox.isSelected();
                    staff = new Ferrier(name, hasCertification);
                    break;
            }

            if (staff != null) {
                hms.addStaff(staff);
                JOptionPane.showMessageDialog(staffFrame, "Staff added successfully.");
            } else {
                JOptionPane.showMessageDialog(staffFrame, "Failed to add staff.");
            }
        });

        staffFrame.add(nameLabel);
        staffFrame.add(nameField);
        staffFrame.add(typeLabel);
        staffFrame.add(typeComboBox);
        staffFrame.add(hoursLabel);
        staffFrame.add(hoursField);
        staffFrame.add(ageLabel);
        staffFrame.add(ageField);
        staffFrame.add(certificationLabel);
        staffFrame.add(certificationBox);
        staffFrame.add(specialtyLabel);
        staffFrame.add(specialtyComboBox);
        staffFrame.add(new JLabel());
        staffFrame.add(submitButton);

        staffFrame.setVisible(true);
    }

    private static void showAddHorsePanel(JFrame frame) {
        JFrame horseFrame = new JFrame("Add Horse");
        horseFrame.setSize(400, 300);
        horseFrame.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel breedLabel = new JLabel("Breed:");
        JTextField breedField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        String[] genders = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        JLabel specialtyLabel = new JLabel("Specialty:");
        String[] specialties = {"Dressage", "Jumping", "Eventing"};
        JComboBox<String> specialtyComboBox = new JComboBox<>(specialties);

        JButton submitButton = new JButton("Add Horse");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String breed = breedField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderComboBox.getSelectedItem();
            String specialty = (String) specialtyComboBox.getSelectedItem();

            Horse horse = new Horse(name, breed, age, gender, specialty);
            hms.addHorse(horse);

            JOptionPane.showMessageDialog(horseFrame, "Horse added successfully.");
        });

        horseFrame.add(nameLabel);
        horseFrame.add(nameField);
        horseFrame.add(breedLabel);
        horseFrame.add(breedField);
        horseFrame.add(ageLabel);
        horseFrame.add(ageField);
        horseFrame.add(genderLabel);
        horseFrame.add(genderComboBox);
        horseFrame.add(specialtyLabel);
        horseFrame.add(specialtyComboBox);
        horseFrame.add(new JLabel());
        horseFrame.add(submitButton);

        horseFrame.setVisible(true);
    }

    private static void showViewAllPanel(JFrame frame) {
        JFrame viewAllFrame = new JFrame("View All");
        viewAllFrame.setSize(800, 600);
        viewAllFrame.setLayout(new BorderLayout());

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        sb.append("Staff:\n");
        for (Staff staff : hms.getStaffList()) {
            sb.append(staff.getName()).append(", Specialty: ").append(staff.getSpecialty()).append("\n");
        }
        sb.append("\nHorses:\n");
        for (Horse horse : hms.getHorseList()) {
            sb.append(horse.getName()).append(", Breed: ").append(horse.getBreed())
                    .append(", Age: ").append(horse.getAge())
                    .append(", Gender: ").append(horse.getGender())
                    .append(", Specialty: ").append(horse.getSpecialty())
                    .append(", Assigned Staff: ")
                    .append(horse.getAssignedStaff() != null ? horse.getAssignedStaff().getName() : "None")
                    .append("\n");
        }

        displayArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(displayArea);
        viewAllFrame.add(scrollPane, BorderLayout.CENTER);

        viewAllFrame.setVisible(true);
    }

    private static void assignHorseToStaff(JFrame frame) {
        JFrame assignFrame = new JFrame("Assign Horse to Staff");
        assignFrame.setSize(400, 200);
        assignFrame.setLayout(new GridLayout(3, 2));

        JLabel staffLabel = new JLabel("Select Staff:");
        JLabel horseLabel = new JLabel("Select Horse:");

        List<Staff> staffList = hms.getStaffList();
        List<Horse> horseList = hms.getHorseList();

        if (staffList.isEmpty() || horseList.isEmpty()) {
            JOptionPane.showMessageDialog(assignFrame, "No staff or horses available for assignment.");
            return;
        }

        JComboBox<String> staffComboBox = new JComboBox<>();
        JComboBox<String> horseComboBox = new JComboBox<>();

        for (Staff staff : staffList) {
            staffComboBox.addItem(staff.getName());
        }

        for (Horse horse : horseList) {
            horseComboBox.addItem(horse.getName());
        }

        JButton assignButton = new JButton("Assign");
        assignButton.addActionListener(e -> {
            String selectedStaffName = (String) staffComboBox.getSelectedItem();
            String selectedHorseName = (String) horseComboBox.getSelectedItem();

            Staff selectedStaff = hms.findStaffByName(selectedStaffName);
            Horse selectedHorse = hms.findHorseByName(selectedHorseName);

            if (selectedStaff != null && selectedHorse != null) {
                selectedStaff.assignHorse(selectedHorse);
                JOptionPane.showMessageDialog(assignFrame, "Horse assigned to staff successfully.");
            } else {
                JOptionPane.showMessageDialog(assignFrame, "Failed to assign horse to staff.");
            }
        });

        assignFrame.add(staffLabel);
        assignFrame.add(staffComboBox);
        assignFrame.add(horseLabel);
        assignFrame.add(horseComboBox);
        assignFrame.add(new JLabel());
        assignFrame.add(assignButton);

        assignFrame.setVisible(true);
    }

    private static void showDutiesPanel(JFrame frame) {
        JFrame dutiesFrame = new JFrame("Perform Duties");
        dutiesFrame.setSize(400, 200);
        dutiesFrame.setLayout(new GridLayout(2, 1));

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        for (Staff staff : hms.getStaffList()) {
            sb.append(staff.getName()).append(" is performing duties...\n");
            staff.performDuties();
            sb.append("\n");
        }

        displayArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(displayArea);
        dutiesFrame.add(scrollPane, BorderLayout.CENTER);

        dutiesFrame.setVisible(true);
    }
}

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
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Horse Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1));

        JButton addStaffButton = new JButton("Add Staff");
        JButton addHorseButton = new JButton("Add Horse");
        JButton assignHorseButton = new JButton("Assign Horse to Staff");
        JButton viewStaffButton = new JButton("View All Staff");
        JButton viewHorsesButton = new JButton("View All Horses");
        JButton performDutiesButton = new JButton("Perform Duties");
        JButton exitButton = new JButton("Exit");

        addStaffButton.setPreferredSize(new Dimension(200, 40));
        addHorseButton.setPreferredSize(new Dimension(200, 40));
        assignHorseButton.setPreferredSize(new Dimension(200, 40));
        viewStaffButton.setPreferredSize(new Dimension(200, 40));
        viewHorsesButton.setPreferredSize(new Dimension(200, 40));
        performDutiesButton.setPreferredSize(new Dimension(200, 40));
        exitButton.setPreferredSize(new Dimension(200, 40));

        addStaffButton.addActionListener(e -> showAddStaffPanel(frame));
        addHorseButton.addActionListener(e -> showAddHorsePanel(frame));
        assignHorseButton.addActionListener(e -> assignHorseToStaff(frame));
        viewStaffButton.addActionListener(e -> showViewStaffPanel(frame));
        viewHorsesButton.addActionListener(e -> showViewHorsesPanel(frame));
        performDutiesButton.addActionListener(e -> showDutiesPanel(frame));
        exitButton.addActionListener(e -> System.exit(0));

        menuPanel.add(addStaffButton);
        menuPanel.add(addHorseButton);
        menuPanel.add(assignHorseButton);
        menuPanel.add(viewStaffButton);
        menuPanel.add(viewHorsesButton);
        menuPanel.add(performDutiesButton);
        menuPanel.add(exitButton);

        frame.add(menuPanel, BorderLayout.WEST);
        frame.setVisible(true);
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
        specialtyLabel.setVisible(false);
        specialtyComboBox.setVisible(false);

        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            hoursLabel.setVisible("Trainer".equals(selectedType));
            hoursField.setVisible("Trainer".equals(selectedType));
            ageLabel.setVisible("Groomer".equals(selectedType));
            ageField.setVisible("Groomer".equals(selectedType));
            certificationLabel.setVisible("Ferrier".equals(selectedType));
            certificationBox.setVisible("Ferrier".equals(selectedType));
            specialtyLabel.setVisible(!"Ferrier".equals(selectedType));
            specialtyComboBox.setVisible(!"Ferrier".equals(selectedType));
        });

        JButton addButton = new JButton("Add Staff");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String type = (String) typeComboBox.getSelectedItem();
            String specialty = (String) specialtyComboBox.getSelectedItem();

            if ("Trainer".equals(type)) {
                int trainingHours = Integer.parseInt(hoursField.getText());
                hms.addStaff(new Trainer(name, specialty, trainingHours));
            } else if ("Veterinarian".equals(type)) {
                hms.addStaff(new Veterinarian(name, specialty));
            } else if ("Groomer".equals(type)) {
                int age = Integer.parseInt(ageField.getText());
                if (age < 18) {
                    JOptionPane.showMessageDialog(frame, "Groomers must be at least 18 years old.");
                    return;
                }
                hms.addStaff(new Groomer(name, age, specialty));
            } else if ("Ferrier".equals(type)) {
                boolean isCertified = certificationBox.isSelected();
                hms.addStaff(new Ferrier(name, isCertified));
            }

            staffFrame.dispose();
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
        staffFrame.add(addButton);

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
        String[] genders = {"Stallion", "Mare", "Gelding"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        JLabel specialtyLabel = new JLabel("Specialty:");
        String[] specialties = {"Dressage", "Jumping", "Eventing"};
        JComboBox<String> specialtyComboBox = new JComboBox<>(specialties);

        JButton addButton = new JButton("Add Horse");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String breed = breedField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderComboBox.getSelectedItem();
            String specialty = (String) specialtyComboBox.getSelectedItem();

            Horse horse = new Horse(name, breed, age, gender, specialty);
            hms.addHorse(horse);
            horseFrame.dispose();
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
        horseFrame.add(addButton);

        horseFrame.setVisible(true);
    }

    private static void assignHorseToStaff(JFrame frame) {
        JFrame assignFrame = new JFrame("Assign Horse to Staff");
        assignFrame.setSize(400, 300);
        assignFrame.setLayout(new GridLayout(4, 2));

        JLabel staffLabel = new JLabel("Select Staff:");
        JLabel horseLabel = new JLabel("Select Horse:");

        JComboBox<String> staffComboBox = new JComboBox<>();
        JComboBox<String> horseComboBox = new JComboBox<>();

        // Populate JComboBoxes with staff and horse names
        for (Staff staff : hms.getStaffList()) {
            staffComboBox.addItem(staff.getName());
        }
        for (Horse horse : hms.getHorseList()) {
            horseComboBox.addItem(horse.getName());
        }

        JButton assignButton = new JButton("Assign");
        assignButton.addActionListener(e -> {
            String selectedStaffName = (String) staffComboBox.getSelectedItem();
            String selectedHorseName = (String) horseComboBox.getSelectedItem();

            Staff selectedStaff = hms.findStaffByName(selectedStaffName);
            Horse selectedHorse = hms.findHorseByName(selectedHorseName);

            if (selectedStaff != null && selectedHorse != null) {
                boolean canAssign = true;
                StringBuilder message = new StringBuilder();

                // Rule 1: Groom over age 50 cannot handle stallions
                if (selectedStaff instanceof Groomer && ((Groomer) selectedStaff).getAge() > 50 && "Stallion".equals(selectedHorse.getGender())) {
                    canAssign = false;
                    message.append("Groomers over 50 cannot handle stallions.\n");
                }

                // Rule 2: Staff and horse specialty match, except for Eventing
                if (!"Eventing".equals(selectedHorse.getSpecialty()) && !selectedHorse.getSpecialty().equals(selectedStaff.getSpecialty())) {
                    canAssign = false;
                    message.append("Staff and horse specialties do not match. Only Eventing horses can be worked on by any staff.\n");
                }

                // Assign horse if all rules are satisfied
                if (canAssign) {
                    selectedStaff.assignHorse(selectedHorse);
                    JOptionPane.showMessageDialog(frame, "Horse assigned to staff successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, message.toString());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Error in assigning horse to staff. Please ensure both are selected correctly.");
            }
            assignFrame.dispose();
        });

        assignFrame.add(staffLabel);
        assignFrame.add(staffComboBox);
        assignFrame.add(horseLabel);
        assignFrame.add(horseComboBox);
        assignFrame.add(new JLabel());
        assignFrame.add(assignButton);

        assignFrame.setVisible(true);
    }

    private static void showViewStaffPanel(JFrame frame) {
        JFrame viewFrame = new JFrame("View All Staff");
        viewFrame.setSize(400, 300);
        viewFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        StringBuilder staffInfo = new StringBuilder();

        for (Staff staff : hms.getStaffList()) {
            staffInfo.append("Name: ").append(staff.getName()).append(", Type: ").append(staff.getClass().getSimpleName()).append(", Specialty: ").append(staff.getSpecialty()).append("\n");
        }

        textArea.setText(staffInfo.toString());

        viewFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        viewFrame.setVisible(true);
    }

    private static void showViewHorsesPanel(JFrame frame) {
        JFrame viewFrame = new JFrame("View All Horses");
        viewFrame.setSize(400, 300);
        viewFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        StringBuilder horseInfo = new StringBuilder();

        for (Horse horse : hms.getHorseList()) {
            horseInfo.append("Name: ").append(horse.getName()).append(", Breed: ").append(horse.getBreed()).append(", Age: ").append(horse.getAge()).append(", Gender: ").append(horse.getGender()).append(", Specialty: ").append(horse.getSpecialty()).append("\n");
        }

        textArea.setText(horseInfo.toString());

        viewFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        viewFrame.setVisible(true);
    }

    private static void showDutiesPanel(JFrame frame) {
        JFrame dutiesFrame = new JFrame("Perform Duties");
        dutiesFrame.setSize(400, 300);
        dutiesFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        StringBuilder dutiesInfo = new StringBuilder();

        for (Staff staff : hms.getStaffList()) {
            dutiesInfo.append(staff.getName()).append(" is performing duties.\n");
            staff.performDuties();
        }

        textArea.setText(dutiesInfo.toString());

        dutiesFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        dutiesFrame.setVisible(true);
    }
}

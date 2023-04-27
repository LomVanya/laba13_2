package core;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StudentListGUI extends JFrame {

    private JList<String> list;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;

    private Map<String, Student> students;

    private static final String DATA_FILE_NAME = "students.dat";

    public StudentListGUI() {
        super("Student List");

        students = new HashMap<>();


        try {
            loadDataFromFile(DATA_FILE_NAME);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
        }

        list = new JList<>(students.keySet().toArray(new String[0]));
        list.addListSelectionListener(e -> {
            String selectedName = list.getSelectedValue();
            Student selectedStudent = students.get(selectedName);
            nameLabel.setText(selectedName);
            ageLabel.setText(Integer.toString(selectedStudent.getAge()));
            addressLabel.setText(selectedStudent.getAddress());
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Имя:"));
        nameLabel = new JLabel();
        panel.add(nameLabel);
        panel.add(new JLabel("Возраст:"));
        ageLabel = new JLabel();
        panel.add(ageLabel);
        panel.add(new JLabel("Адрес:"));
        addressLabel = new JLabel();
        panel.add(addressLabel);



        JButton addButton = new JButton("Добавить студента");
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Имя:");
            if (name != null && !name.isEmpty()) {
                int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Возраст:"));
                String address = JOptionPane.showInputDialog(this, "Адрес:");
                Student newStudent = new Student(name, age, address);
                students.put(name, newStudent);
                list.setListData(students.keySet().toArray(new String[0]));
            }
        });

        JButton deleteButton = new JButton("Удалить студента");
        deleteButton.addActionListener(e -> {
            String selectedName = list.getSelectedValue();
            if (selectedName != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Удалить студента " + selectedName + "?");
                if (confirm == JOptionPane.YES_OPTION) {
                    students.remove(selectedName);
                    list.setListData(students.keySet().toArray(new String[0]));
                    nameLabel.setText(null);
                    ageLabel.setText(null);
                    addressLabel.setText(null);
                    try {
                        saveDataToFile(DATA_FILE_NAME);
                        JOptionPane.showMessageDialog(this, "Студент удален.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Выберите студента для удаления.");
            }
        });

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            try {
                saveDataToFile(DATA_FILE_NAME);
                JOptionPane.showMessageDialog(this, "Успешно!.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);



        add(new JScrollPane(list), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadDataFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                students = (Map<String, Student>) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Не удалось сохранить.", e);
            }
        }
    }

    private void saveDataToFile(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(students);
        }
    }

    public static void main(String[] args) {
        new StudentListGUI();
    }

    private static class Student implements Serializable {
        private String name;
        private int age;
        private String address;

        public Student(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }
    }
}

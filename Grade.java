import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Grade {

    public static void main(String[] args) {
        // Number of subjects
        int numberOfSubjects ;
        String nm;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your name");
        nm=sc.next();
        System.out.println("Enter no of Subjects");
        numberOfSubjects = sc.nextInt();
        // Create label and text field for each subject
        JLabel[] subjectLabels = new JLabel[numberOfSubjects];
        JTextField[] subjectFields = new JTextField[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + ": ");
            subjectFields[i] = new JTextField(5);
        }

        // Create text fields for total marks, average percentage, and grade
        JTextField totalMarksField = new JTextField(5);
        totalMarksField.setEditable(false);
        JTextField averagePercentageField = new JTextField(5);
        averagePercentageField.setEditable(false);
        JTextField gradeField = new JTextField(5);
        gradeField.setEditable(false);

        // Create buttons for calculate and exit
        JButton calculateButton = new JButton("Calculate");
        JButton exitButton = new JButton("Exit");

        // Create panel for labels and text fields
        JPanel inputPanel = new JPanel(new GridLayout(numberOfSubjects + 3, 2));
        for (int i = 0; i < numberOfSubjects; i++) {
            inputPanel.add(subjectLabels[i]);
            inputPanel.add(subjectFields[i]);
        }
        inputPanel.add(new JLabel("Total Marks: "));
        inputPanel.add(totalMarksField);
        inputPanel.add(new JLabel("Average Percentage: "));
        inputPanel.add(averagePercentageField);
        inputPanel.add(new JLabel("Grade: "));
        inputPanel.add(gradeField);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(calculateButton);
        buttonPanel.add(exitButton);

        // Create main frame
        JFrame frame = new JFrame("Student Result Calculator");
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        // Add action listeners to buttons
        calculateButton.addActionListener(e -> {
            // Calculate total marks, average percentage, and grade
            int totalMarks = 0;
            for (JTextField field : subjectFields) {
                try {
                    totalMarks += Integer.parseInt(field.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input in subject field(s). Please enter correct marks.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            double averagePercentage = (double) totalMarks / numberOfSubjects;
            String grade = calculateGrade(averagePercentage);

            // Display results
            totalMarksField.setText(String.valueOf(totalMarks));
            averagePercentageField.setText(String.format("%.2f", averagePercentage));
            System.out.println("Student name "+nm);
            System.out.println("Grade "+grade);
            gradeField.setText(grade);


        });

        exitButton.addActionListener(e -> System.exit(0));
        sc.close();
    }

    // Function to calculate grade based on average percentage
    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
        
    }
}

package com.rental;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentalGUI extends JFrame {
    

    private JComboBox<String> vehicleDropdown;
    private JTextField daysField;
    private JTextArea outputArea;
    private RentalManager manager;

    public RentalGUI() {

        manager = new RentalManager();
        loadSampleData();


        setTitle("Vehicle Rental System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));


        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Select Vehicle:"));
        vehicleDropdown = new JComboBox<>();
        refreshDropdown();
        row1.add(vehicleDropdown);


        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Rental Days:"));
        daysField = new JTextField(5);
        row2.add(daysField);
        
        JButton rentButton = new JButton("Rent Vehicle");
        rentButton.setBackground(new Color(50, 150, 50)); 
        rentButton.setForeground(Color.WHITE);
        row2.add(rentButton);

        topPanel.add(row1);
        topPanel.add(row2);
        add(topPanel, BorderLayout.NORTH);

  
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("System Log"));
        add(scrollPane, BorderLayout.CENTER);

   
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRentButton();
            }
        });

 
        displayFleet();
    }



    private void loadSampleData() {
        manager.addVehicle(new Car("C001", "Toyota", "Camry", 50.0));
        manager.addVehicle(new Motorcycle("M001", "Honda", "CBR", 30.0));
        manager.addVehicle(new Truck("T001", "Ford", "F-150", 80.0, 2000));
    }

    private void refreshDropdown() {
        vehicleDropdown.removeAllItems();
  
        for (Vehicle v : manager.getFleet()) { 
            vehicleDropdown.addItem(v.getVehicleId() + " - " + v.getBrand() + " " + v.getModel() + 
                                   (v.isAvailable() ? " [Available]" : " [RENTED]"));
        }
    }

    private void displayFleet() {
        outputArea.append("--- Current Fleet Status ---\n");
        for (Vehicle v : manager.getFleet()) {
            outputArea.append(v.toString() + " - " + (v.isAvailable() ? "Available" : "RENTED") + "\n");
        }
        outputArea.append("\n");
    }

    private void handleRentButton() {
  
        String selected = (String) vehicleDropdown.getSelectedItem();
        if (selected == null) return;
        
     
        String vehicleId = selected.substring(0, 4);

        try {
            int days = Integer.parseInt(daysField.getText());
            double price = manager.rentVehicle(vehicleId, days);
            
            outputArea.append("SUCCESS: Rented " + vehicleId + " for " + days + " days. Cost: $" + price + "\n");
            refreshDropdown();"
            daysField.setText(""); 

        } catch (NumberFormatException ex) {
            outputArea.append("ERROR: Please enter a valid number for days.\n");
        } catch (RentalException ex) {
            outputArea.append("ERROR: " + ex.getMessage() + "\n");
        }
    }


}

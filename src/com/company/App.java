package com.company;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;


public class App {

    private JTextField searchField;
    private JTextField name;
    private JTextField director;
    private JTextField year;
    private JFrame frame;
    private JButton addButton, deleteButton, updateButton;
    private JList<Movie> list1;
    //private JList list2;
    private IMyListModel model;
    private JMenuBar menubar;


    public App() {
        initialize(); // implementerar metoden initialize.

        createMenuBar(); // lägger till metoden så att menyn fungerar i gui.
        model = new FilteredListModel();
        list1.setModel(model);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Actionlistener metod för att lägga till.
                Movie m = Factory.createMovie(name.getText(), director.getText(), year.getText());
                model.add(m);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            //Actionlistener metod för att ta bort.
            @Override
            public void actionPerformed(ActionEvent e) {
                model.remove(list1.getSelectedIndex());
            }
        });

        updateButton.addActionListener(new ActionListener() {
            //Actionlistener metod för att Updatea.
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Movie m = Factory.createMovie(name.getText(), director.getText(), year.getText());
                    model.update(list1.getSelectedIndex(), m);
                }catch(NumberFormatException n){
                    year.setText("");
                }catch(IllegalArgumentException il){
                    System.out.println("Fält är tomt");
                }


              /* Movie s = list1.getSelectedValue();
               s.setTitle(name.getText());
               s.setDirector(director.getText());
               s.setYear(Integer.parseInt(year.getText()));
                //model.update(list1.getSelectedIndex(), name.getText());
                frame.getContentPane().repaint();*/
            }
        });

    }


    private void initialize() {


        // metod som skapar alla värden för guifönstret(knappar, storlek osv)
        frame = new JFrame("IMDB");
        frame.setBounds(100, 100, 499, 382);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        addButton = new JButton("Lägg till ");
        deleteButton = new JButton("Ta bort ");
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


            }


        });


        addButton.setBounds(10, 301, 89, 23); // sätter position/storlek på knapp.
        frame.getContentPane().add(addButton);


        deleteButton.setBounds(126, 301, 89, 23);
        frame.getContentPane().add(deleteButton);

        updateButton = new JButton("Ändra ");
        updateButton.setBounds(251, 301, 89, 23);
        frame.getContentPane().add(updateButton);


        searchField = new JTextField(); // sätter position/storlek på textfield.
        searchField.setBounds(353, 11, 110, 25);
        frame.getContentPane().add(searchField);
        searchField.setColumns(10);

        list1 = new JList();
        list1.setBounds(50, 104, 385, 200);
        frame.getContentPane().add(list1);

        JLabel läggaTill = new JLabel("Titel: ");
        läggaTill.setBounds(50, 35, 100, 20);
        frame.getContentPane().add(läggaTill);

        name = new JTextField();
        name.setBounds(86, 35, 253, 23);
        frame.getContentPane().add(name);
        name.setColumns(10);

        JLabel läggTillDirector = new JLabel("Regissör: ");
        läggTillDirector.setBounds(30, 58, 100, 20);
        frame.getContentPane().add(läggTillDirector);


        director = new JTextField();
        director.setBounds(86, 58, 253, 23);
        frame.getContentPane().add(director);
        director.setColumns(10);

        JLabel läggTillÅr = new JLabel("År:");
        läggTillÅr.setBounds(50, 81, 100, 20);
        frame.getContentPane().add(läggTillÅr);


        year = new JTextField();
        year.setBounds(86, 81, 253, 23);
        frame.getContentPane().add(year);
        year.setColumns(10);

        JLabel search = new JLabel("Sök:");
        search.setBounds(280, 15, 60, 14);
        frame.getContentPane().add(search);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            //documentlistener för när man ska ändra info
            @Override
            public void insertUpdate(DocumentEvent e) {
                model.filter(new Contains(searchField.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                model.filter(new Contains(searchField.getText()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //model.filter(new Contains(textField_1.setText());

            }
        });


    }

   /* private void updateButtonActionPerformed(ActionEvent evt)
    {

        int index = list1.getSelectedIndex();
        model.setElementAt(textField.getText(), index);

        textField.setText("");

    }*/




        /*
        l -> {
                if (!textField_1.getText().isEmpty()) {
                    System.out.println("blabla");

                }
        }
         */


    private void createMenuBar() {
        //metod för att skapa menyfliken
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu menu = new JMenu("Meny");
        menubar.add(menu);
        // kod för att kunna öppna/spara en fil
        JMenuItem openFileMenu = new JMenuItem("Öppna",
                KeyEvent.VK_O);
        openFileMenu.getAccessibleContext().setAccessibleDescription(
                "Select a file to open");
        openFileMenu.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menu.add(openFileMenu);

        openFileMenu.addActionListener(this::openFile);

        JMenuItem saveFileMenu = new JMenuItem("Spara ",
                KeyEvent.VK_S);
        saveFileMenu.getAccessibleContext().setAccessibleDescription(
                "Save to file");
        saveFileMenu.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menu.add(saveFileMenu);

        saveFileMenu.addActionListener(this::saveFile);

        JMenuItem exitWindowMenu = new JMenuItem("Avsluta",
                KeyEvent.VK_Q);
        exitWindowMenu.getAccessibleContext().setAccessibleDescription("Exit Window");
        exitWindowMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menu.add(exitWindowMenu);


        exitWindowMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                System.exit(0);


            }
        });


    }

    /*private void exitWindow(ActionEvent actionEvent) {

    }*/

    private void openFile(ActionEvent actionEvent) {
        //metoden för att öppna fil
        final JFileChooser fc = new JFileChooser();

        int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            loadFile(file);
        }
    }


    private void loadFile(File file) {
        // metod för att läsa in filen
        new Thread(() -> {

            BufferedReader inputStream = null;

            try {
                inputStream = new BufferedReader(new FileReader(file));

                String temp;
                model.clear();
               while ((temp = inputStream.readLine()) != null) {

                   String[] parts = temp.split(",");
                    model.add(new Movie(parts[0].substring(7), parts[1].substring(10),Integer.parseInt(parts[2].substring(7))));
               }


            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public void saveFile(ActionEvent e) {
        final JFileChooser fc = new JFileChooser();

        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            new Thread(() -> {
                File file = fc.getSelectedFile();
                try {
                    PrintWriter outputStream = new PrintWriter(new FileWriter(file));
                    for (int i = 0; i < model.getSize(); i++) {
                        outputStream.println(model.getElementAt(i));
                    }
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }).start();
        }
    }


    public static void main(String[] args) {
        //main metoden för att kunna köra programmet.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App window = new App();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}








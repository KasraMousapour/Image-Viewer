//package com.kasra;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;


public class ImageViewerGUI extends JFrame implements ActionListener {
    JButton selectFileButton = new JButton("Choose Image");
    JButton showImageButton = new JButton("Show Image");
    JButton resizeButton = new JButton("Resize");
    JButton grayscaleButton = new JButton("Gray scale");
    JButton brightnessButton = new JButton("brightness");
    JButton closeButton = new JButton("Exit");

    JButton[] functionButtons = {selectFileButton, showImageButton, resizeButton, grayscaleButton, brightnessButton, closeButton};
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "\\home\\...";
    public File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        mainPanel();
        this.setVisible(true);
        this.setResizable(true);

    }

    public void mainPanel() {
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));

        buttonsPanel.setBounds(100, 100, 500, 100);

        //styling mainPanel buttons
        for (int i = 0; i < 6; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setBackground(Color.WHITE);
            functionButtons[i].setForeground(Color.BLACK);
            functionButtons[i].setFocusable(false);
        }

        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }

    public void resizePanel() {
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);

        //Create resizePanel elements and set their bounds
        JLabel resizeSectionLabel = new JLabel("Resize section", JLabel.CENTER);
        resizeSectionLabel.setBounds(300, 40, 100, 50);

        JLabel widthLabel = new JLabel("Width:", JLabel.RIGHT);
        widthLabel.setBounds(100, 90, 190, 30);

        JLabel heightLabel = new JLabel("Height:", JLabel.RIGHT);
        heightLabel.setBounds(100, 120, 190, 30);

        widthTextField = new JTextField();
        widthTextField.setBounds(300, 90, 100, 30);

        heightTextField = new JTextField();
        heightTextField.setBounds(300, 120, 100, 30);

        backButton = new JButton("Back");
        backButton.setBounds(100, 180, 75, 30);

        showResizeButton = new JButton("Show Result");
        showResizeButton.setBounds(450, 180, 150, 30);


        //styling resizePanel buttons
        JButton[] resizeButtonsArr = {showResizeButton, backButton};
        for (JButton button : resizeButtonsArr) {
            button.addActionListener(this);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFocusable(false);
        }

        //Adding all elements to resizePanel
        resizePanel.add(resizeSectionLabel);
        resizePanel.add(widthLabel);
        resizePanel.add(heightLabel);
        resizePanel.add(widthTextField);
        resizePanel.add(heightTextField);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);

        //Add resizePanel to main Frame
        this.add(resizePanel);
    }

    public void brightnessPanel() {
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        brightnessPanel.setSize(700, 300);

        //Create elements
        JLabel brightnessLabel = new JLabel("Enter f (must be between 0 and 1):", JLabel.LEFT);
        brightnessLabel.setFont(new Font("time", Font.BOLD, 15));
        brightnessLabel.setBounds(125, 100, 275, 50);

        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(400, 100, 150, 50);

        backButton = new JButton("Back");
        backButton.setBounds(125, 170, 75, 30);

        showBrightnessButton = new JButton("Result");
        showBrightnessButton.setBounds(475, 170, 75, 30);

        //styling brightnessPanel buttons
        JButton[] brightnessButtonsArr = {showBrightnessButton, backButton};
        for (JButton button : brightnessButtonsArr) {
            button.addActionListener(this);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFocusable(false);
        }


        //Adding elements to panel
        brightnessPanel.add(brightnessLabel);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(backButton);

        //Adding panel to main Frame
        this.add(brightnessPanel);
        brightnessPanel.setVisible(true);
    }

    public void chooseFileImage() {
        try {
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();}
        }catch (Exception e){
            //Showing alert when occurring an exception
            JFrame alertFrame = new JFrame();
            JOptionPane.showMessageDialog(alertFrame, "Wrong !!" + e.getMessage());
        }

    }

    public void showOriginalImage() {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        //Set a container to Chosen Image
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
        JLabel pictureLabel = new JLabel(imageIcon);


        //Add Label to panel
        tempPanel.add(pictureLabel);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage() throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        //Converting Image
        BufferedImage image = ImageIO.read(file);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        image = op.filter(image, null);

        //Set a container to hold converted Image
        ImageIcon newImage = new ImageIcon(image.getScaledInstance(800,600,Image.SCALE_DEFAULT));
        JLabel newImageLabel = new JLabel(newImage);

        //Add Label to panel
        tempPanel.add(newImageLabel);


        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void showResizeImage(int w, int h) {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        try {
            w = Integer.parseInt(widthTextField.getText());
            h = Integer.parseInt(heightTextField.getText());

            //Resizing image
            ImageIcon oldImageIcon = new ImageIcon(String.valueOf(file));
            Image scaleImage = oldImageIcon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);

            //Set a container to resized image
            ImageIcon newImageIcon = new ImageIcon(scaleImage);
            JLabel newImageLabel = new JLabel(newImageIcon);

            //Add label to panel
            tempPanel.add(newImageLabel);


            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (Exception e) {
            //Showing alert when occurring an exception
            JFrame alertFrame = new JFrame();
            JOptionPane.showMessageDialog(alertFrame, "Wrong value!!Please write a number.");
        }
    }

    public void showBrightnessImage(float f) {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        try {
            f = Float.parseFloat(brightnessTextField.getText());
            if (!(f > 0 && f < 1)) throw new Exception();
            else {
                //Converting image
                BufferedImage image = ImageIO.read(file);
                RescaleOp op = new RescaleOp(f, 0, null);
                image = op.filter(image, image);

                //Set a container to hold image
                ImageIcon newImage = new ImageIcon(image.getScaledInstance(800,600,Image.SCALE_DEFAULT));
                JLabel newImageLabel = new JLabel(newImage);

                //Add label to panel
                tempPanel.add(newImageLabel);

                tempPanel.setSize(1800, 1000);
                tempFrame.setTitle("Image Viewer");
                tempFrame.setSize(1800, 1000);
                tempFrame.setVisible(true);
                tempFrame.setResizable(true);
                tempFrame.add(tempPanel);
            }
        } catch (Exception e) {
            //Showing alert when occurring an exception
            JFrame alertFrame = new JFrame();
            JOptionPane.showMessageDialog(alertFrame, "Wrong value!!Please write a number between 0 and 1.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resizeButton) {
            this.getContentPane().removeAll();
            this.resizePanel();
            this.revalidate();
            this.repaint();

        } else if (e.getSource() == showImageButton) {
            showOriginalImage();

        } else if (e.getSource() == grayscaleButton) {
            try {
                grayScaleImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == showResizeButton) {
            try {
                showResizeImage(w, h);
            } catch (Exception ex) {
                JFrame alertFrame = new JFrame();
                JOptionPane.showMessageDialog(alertFrame, "Wrong!!" + ex.getMessage());
            }

        } else if (e.getSource() == brightnessButton) {
            this.getContentPane().removeAll();
            this.brightnessPanel();
            this.revalidate();
            this.repaint();


        } else if (e.getSource() == showBrightnessButton) {
            try {
                showBrightnessImage(brightenFactor);
            } catch (Exception ex) {
                JFrame alertFrame = new JFrame();
                JOptionPane.showMessageDialog(alertFrame, "Wrong!!" + ex.getMessage());
            }

        } else if (e.getSource() == selectFileButton) {
            chooseFileImage();

        } else if (e.getSource() == closeButton) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == backButton) {
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }

    public static void main(String[] args) {
        new ImageViewerGUI();
    }
}

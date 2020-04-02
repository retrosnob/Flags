import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class MainForm {

    private JPanel pnlMain;
    private JButton btnSubmit;
    private JTextField txtInput;
    private JLabel lblImage;
    private JLabel lblResult;
    private JLabel lblScore;
    private JButton btnNewGame;
    private JList<String> lstCountries;
    private JScrollPane scrlCountries;
    private JFrame frame;

    public MainForm() {
        Model model = new Model();
        model.newQuiz();
        pnlMain.setPreferredSize(new Dimension(600, 400));

        ImageIcon icon = new ImageIcon("images/" + model.getNextQuestion().fileName);
        lblImage.setIcon(icon);
        lblScore.setText(model.numCorrect + " / " + model.numAttempted);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<String> countries = model.getCountries();
        countries.forEach(listModel::addElement);
        lstCountries.setModel(listModel);
        lstCountries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnSubmit.addActionListener(e -> {
            String selected = lstCountries.getSelectedValue();
            if (selected != null) {
                String correct = model.checkAnswer(selected);
                lblResult.setText(correct);
                // Update the number of correct answers, etc
                ImageIcon icon1 = new ImageIcon("images/" + model.getNextQuestion().fileName);
                lblImage.setIcon(icon1);
                lblScore.setText(model.numCorrect + " / " + model.numAttempted);
                txtInput.setText("");
                txtInput.requestFocus();
            }
        });

        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                lstCountries.setModel(updateListModel(countries));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lstCountries.setModel(updateListModel(countries));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        btnNewGame.addActionListener(e -> {
            model.newQuiz();
            ImageIcon icon12 = new ImageIcon("images/" + model.getNextQuestion().fileName);
            lblImage.setIcon(icon12);
            lblScore.setText(model.numCorrect + " / " + model.numAttempted);
        });

        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    lstCountries.setSelectedIndex(0);
                    lstCountries.requestFocus();
                }
            }
        });

        lstCountries.addKeyListener(new KeyAdapter() {
            @Overridehowintell
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    // lstCountries.setSelectedIndex(0);
                    txtInput.requestFocus();
                }
            }
        });


        frame = new JFrame("Flags");
        frame.setContentPane(pnlMain);
        frame.getRootPane().setDefaultButton(btnSubmit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        txtInput.addKeyListener(new KeyAdapter() {
        });
    }

    DefaultListModel<String> updateListModel(List<String> countries) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        countries.forEach(
                element -> {
                    if (element.toLowerCase().contains(txtInput.getText().toLowerCase())) {
                        listModel.addElement(element);
                    }
                    else if (txtInput.getText().equals("")) {
                        listModel.addElement(element);
                    }
                }
        );
        return listModel;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new MainForm().frame.setVisible(true));
    }


}

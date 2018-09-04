
package client;

import static client.Cliente.endpoint_addr;
import shared.Functions;
import java.rmi.Naming;
import javax.swing.JOptionPane;


@SuppressWarnings("deprecation")
public class InterfaceCliente extends javax.swing.JFrame {
    
    static String endpoint_addr = "10.42.0.107:1099";
    double p,a,imc,imcatualizado;
    String paciente;
    
    public InterfaceCliente() {
        initComponents();
        setSize(850,510);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        altura = new javax.swing.JLabel();
        pesoperde = new javax.swing.JLabel();
        massapg = new javax.swing.JTextField();
        imcc = new javax.swing.JTextField();
        imc1 = new javax.swing.JLabel();
        calculoimc = new javax.swing.JButton();
        alturapaciente = new javax.swing.JTextField();
        pesopaciente = new javax.swing.JTextField();
        peso = new javax.swing.JLabel();
        nomepaciente = new javax.swing.JTextField();
        nome = new javax.swing.JLabel();
        INDICEMASSACORPORAL = new javax.swing.JLabel();
        backatv2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        altura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        altura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        altura.setText("ALTURA (m):");
        getContentPane().add(altura);
        altura.setBounds(30, 280, 120, 20);

        pesoperde.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pesoperde.setText("GANHO/PERCA MASSA");
        getContentPane().add(pesoperde);
        pesoperde.setBounds(320, 370, 160, 17);

        massapg.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        getContentPane().add(massapg);
        massapg.setBounds(350, 390, 90, 23);

        imcc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        imcc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(imcc);
        imcc.setBounds(80, 390, 90, 23);

        imc1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        imc1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imc1.setText("IMC");
        getContentPane().add(imc1);
        imc1.setBounds(110, 370, 34, 14);

        calculoimc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        calculoimc.setText("CALCULAR IMC");
        calculoimc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculoimcActionPerformed(evt);
            }
        });
        getContentPane().add(calculoimc);
        calculoimc.setBounds(390, 220, 120, 23);

        alturapaciente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        alturapaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(alturapaciente);
        alturapaciente.setBounds(160, 280, 200, 23);

        pesopaciente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pesopaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(pesopaciente);
        pesopaciente.setBounds(160, 220, 200, 23);

        peso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        peso.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        peso.setText("PESO (kg):");
        getContentPane().add(peso);
        peso.setBounds(50, 220, 100, 17);

        nomepaciente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomepaciente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(nomepaciente);
        nomepaciente.setBounds(160, 160, 200, 23);

        nome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nome.setText("NOME:");
        getContentPane().add(nome);
        nome.setBounds(50, 160, 100, 17);

        INDICEMASSACORPORAL.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        INDICEMASSACORPORAL.setText("ÍNDICE DE MASSA CORPORAL");
        getContentPane().add(INDICEMASSACORPORAL);
        INDICEMASSACORPORAL.setBounds(50, 40, 400, 40);

        backatv2.setIcon(new javax.swing.ImageIcon("/home/will/eclipse-workspace/RMI/src/client/backatv22.png")); // NOI18N
        getContentPane().add(backatv2);
        backatv2.setBounds(0, 0, 850, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calculoimcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculoimcActionPerformed
        
        if(altura.getText().equals("") || nomepaciente.getText().equals("")|| pesopaciente.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"DIGITE O TODOS OS CAMPOS","ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        paciente = nomepaciente.getText();
        p = Float.parseFloat(pesopaciente.getText());
        a = Float.parseFloat(alturapaciente.getText());
        
        imc = p/(a*a);
            
        imcc.setText(String.valueOf(imc));
        
        
        try {
            Functions func = (Functions) Naming.lookup("//" + endpoint_addr + "/functions");
            System.out.println("> Enviando execução do metodo Inserir");
            double diferencaIMC = func.inserir(paciente, imc);
            imcatualizado = diferencaIMC;
            if(diferencaIMC != 0){
                massapg.setText(String.valueOf(imcatualizado));
            }else{
                massapg.setText(String.valueOf(0));
            }
            System.out.println("< Resposta do metodo Inserir: " + diferencaIMC);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
      
        
        
    }//GEN-LAST:event_calculoimcActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel INDICEMASSACORPORAL;
    private javax.swing.JLabel altura;
    private javax.swing.JTextField alturapaciente;
    private javax.swing.JLabel backatv2;
    private javax.swing.JButton calculoimc;
    private javax.swing.JLabel imc1;
    private javax.swing.JTextField imcc;
    private javax.swing.JTextField massapg;
    private javax.swing.JLabel nome;
    private javax.swing.JTextField nomepaciente;
    private javax.swing.JLabel peso;
    private javax.swing.JTextField pesopaciente;
    private javax.swing.JLabel pesoperde;
    // End of variables declaration//GEN-END:variables

 
}

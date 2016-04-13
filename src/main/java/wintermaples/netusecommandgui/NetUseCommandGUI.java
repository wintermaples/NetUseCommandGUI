/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wintermaples.netusecommandgui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wintermaples
 */
public class NetUseCommandGUI extends javax.swing.JFrame {

  /**
   * Creates new form NetUseCommandGUI
   */
  public NetUseCommandGUI() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    UserNameTextField = new javax.swing.JTextField();
    LoginButton = new javax.swing.JButton();
    PasswordTextField = new javax.swing.JPasswordField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("ファイル共有サーバーにログイン");
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    setIconImage(new javax.swing.ImageIcon(getClass().getResource("/1878.png")).getImage());
    setLocationByPlatform(true);
    setResizable(false);

    jLabel1.setFont(new java.awt.Font("メイリオ", 0, 14)); // NOI18N
    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2361.png"))); // NOI18N
    jLabel1.setText("ユーザー名: ");

    jLabel2.setFont(new java.awt.Font("メイリオ", 1, 18)); // NOI18N
    jLabel2.setText("共有サーバーにログイン");

    jLabel3.setFont(new java.awt.Font("メイリオ", 0, 14)); // NOI18N
    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/963.png"))); // NOI18N
    jLabel3.setText("パスワード:");

    UserNameTextField.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

    LoginButton.setFont(new java.awt.Font("メイリオ", 0, 14)); // NOI18N
    LoginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1878.png"))); // NOI18N
    LoginButton.setText("ログイン");
    LoginButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        LoginButtonActionPerformed(evt);
      }
    });

    PasswordTextField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
              .addContainerGap()
              .addComponent(jLabel1)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(UserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
              .addGap(99, 99, 99)
              .addComponent(jLabel2))))
        .addContainerGap(49, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(UserNameTextField))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(LoginButton)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
    String msg = loginAt("example", UserNameTextField.getText(), PasswordTextField.getPassword());
    if (msg.contains("ERR:")) {
      msg = msg.replaceAll("INFO:", "");
      msg = msg.replaceAll("ERR:", "");
      ErrDialog errDialog = new ErrDialog(this, false);
      errDialog.setErrMsg(msg);
      errDialog.setVisible(true);
    } else {
      SuccessDialog successDialog = new SuccessDialog(this, true);
      successDialog.setVisible(true);
    }
  }//GEN-LAST:event_LoginButtonActionPerformed

  /**
   * net useコマンドを使用して他のコンピューターにログインします。
   *
   * @param computerName 接続先のコンピューター名
   * @param userName ユーザー名
   * @param password パスワード
   * @return コマンドが返したメッセージ。通常ストリームの場合は「INFO:」、エラーストリームの場合は「ERR:」が最初についています。何らかの例外が発生した場合は「EXCEPTION:[例外クラス名]」を返します。
   */
  public String loginAt(String computerName, String userName, char[] password) {
    ProcessBuilder pb = new ProcessBuilder("net", "use", "\\\\" + computerName + "\\IPC$", String.valueOf(password), "/user:" + computerName + "\\" + userName);
    try {
      System.out.println(pb.command());
      Process process = pb.start();
      process.waitFor();

      StringBuilder sb = new StringBuilder();
      BufferedReader infoReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      infoReader.lines().forEach(line -> sb.append("INFO:").append(line).append("\n"));
      BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      errReader.lines().forEach(line -> sb.append("ERR:").append(line).append("\n"));

      return sb.toString();
    } catch (IOException | InterruptedException ex) {
      Logger.getLogger(NetUseCommandGUI.class.getName()).log(Level.SEVERE, null, ex);
      return "EXCEPTION:" + ex.getClass().toString();
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(NetUseCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(NetUseCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(NetUseCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(NetUseCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new NetUseCommandGUI().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton LoginButton;
  private javax.swing.JPasswordField PasswordTextField;
  private javax.swing.JTextField UserNameTextField;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  // End of variables declaration//GEN-END:variables
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BelarminoChaleProjectoJava;

import Modulo.Conexao;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author USER
 */
public class FormFonteEnergetica extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form FormFonteEnergetica
     */
    public FormFonteEnergetica() {
        initComponents();

        comboBox();

        conexao = Conexao.conector();

        //System.out.println(conexao);
        preencherF();
        preencherT();
    }

    void preencherT() {

        String sql = "Select t.cod as Código, t.nome as Nome, r.nome as Representante, t.distrito as Distrito from titular t left join representante r on t.cod_rep = r.cod";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    void preencherF() {

        String sql = "Select f.cod as Codigo, t.tipo as Tipo, t.categoria as Categoria, f.distrito as Distrito from fonte f inner join tipo_fonte t on f.tipo_id = t.id";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    //=-=-=-= FUNCIONARIO =-=-=-= FUNCIONARIO =-=-=-= FUNCIONARIO =-=-=-=
    void comboBox() {
        if (jComboBox4.getSelectedIndex() == 0) {
            jComboBox3.setSelectedIndex(0);
        } else if (jComboBox4.getSelectedIndex() == 1) {
            jComboBox3.setSelectedIndex(1);
        } else if (jComboBox4.getSelectedIndex() == 2) {
            jComboBox3.setSelectedIndex(2);
        }
    }

    private int funcDep;

    void registarFunc() {

        String registar = "insert into funcionario(cod, nome, nuit, departamento, categoria, data_nomeacao, email, celular, usuario, senha) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            pst = conexao.prepareStatement(registar);

            pst.setString(1, jTextFieldcod.getText());
            pst.setString(2, jTextField18name.getText());
            pst.setString(3, jTextField19nuit.getText());

            pst.setString(4, String.valueOf(jComboBox1.getSelectedIndex() + 1));
            pst.setString(5, String.valueOf(jComboBox2.getSelectedIndex() + 1));
            pst.setString(6, jFormattedTextField1.getText());
            pst.setString(7, jTextField23enail.getText());
            pst.setString(8, jFormattedTextField3.getText());
            pst.setString(9, jTextField44uso.getText());
            pst.setString(10, jTextField40senha.getText());

            if (jTextFieldcod.getText().isEmpty() || jTextField18name.getText().isEmpty() || jFormattedTextField3.getText().isEmpty() || jTextField44uso.getText().isEmpty() || jTextField40senha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                if (jTextFieldcod.getText().isEmpty()) {

                    jTextFieldcod.setForeground(Color.red);
                    jTextFieldcod.setText("Muito Obrigatório");

                } else if (jTextField18name.getText().isEmpty()) {

                    jTextField18name.setForeground(Color.red);
                    jTextField18name.setText("Obrigatório");

                } else if (jTextField44uso.getText().isEmpty()) {

                    jTextField44uso.setForeground(Color.red);
                    jTextField44uso.setText("Obrigatório");

                } else if (jTextField40senha.getText().isEmpty()) {

                    jTextField40senha.setForeground(Color.red);
                    jTextField40senha.setText("Obrigatório");

                } else if (jFormattedTextField3.getText() == "- -") {

                    jFormattedTextField3.setForeground(Color.red);

                } else if (jFormattedTextField2.getText() == "(+258)") {

                    jFormattedTextField2.setForeground(Color.red);

                }

            } else {
                int ad = JOptionPane.showConfirmDialog(rootPane, "Cadastrar novo Funcionário?", "Cadastro", JOptionPane.YES_NO_OPTION);
                if (ad == JOptionPane.YES_OPTION) {
                    int execute = pst.executeUpdate();
                    if (execute > 0) {
                        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso");

                        jTextFieldcod.setText(null);
                        jTextField18name.setText(null);
                        jTextField19nuit.setText(null);
                        jComboBox1.setSelectedIndex(0);
                        jComboBox2.setSelectedIndex(0);
                        jFormattedTextField1.setText(null);
                        jTextField23enail.setText(null);
                        jFormattedTextField3.setText(null);
                        jTextField44uso.setText(null);
                        jTextField40senha.setText(null);
                        jTextFieldcod.setText(null);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro não sucedido");
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void actualizarFunc() {

        String actualizar = "update funcionario set cod = ?, nome = ?, nuit = ?, departamento = ?, categoria = ?, data_nomeacao = ?, email = ?, celular = ?, usuario = ?, senha = ? where cod = ?";
        try {

            if (!"".equals(jTextFieldcod.getText())) {

                pst = conexao.prepareStatement(actualizar);

                pst.setString(1, jTextFieldcod.getText());
                pst.setString(2, jTextField18name.getText());
                pst.setString(3, jTextField19nuit.getText());
                pst.setString(4, String.valueOf(jComboBox1.getSelectedIndex() + 1));
                pst.setString(5, String.valueOf(jComboBox2.getSelectedIndex() + 1));
                pst.setString(6, jFormattedTextField1.getText());
                pst.setString(7, jTextField23enail.getText());
                pst.setString(8, jFormattedTextField3.getText());
                pst.setString(9, jTextField44uso.getText());
                pst.setString(10, jTextField40senha.getText());
                pst.setString(11, jTextFieldcod.getText());

                if (jTextFieldcod.getText().isEmpty() || jTextField18name.getText().isEmpty() || jFormattedTextField3.getText().isEmpty() || jTextField44uso.getText().isEmpty() || jTextField40senha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                    if (jTextFieldcod.getText().isEmpty()) {

                        jTextFieldcod.setForeground(Color.red);
                        jTextFieldcod.setText("Muito Obrigatório");

                    } else if (jTextField18name.getText().isEmpty()) {

                        jTextField18name.setForeground(Color.red);
                        jTextField18name.setText("Obrigatório");

                    } else if (jTextField44uso.getText().isEmpty()) {

                        jTextField44uso.setForeground(Color.red);
                        jTextField44uso.setText("Obrigatório");

                    } else if (jTextField40senha.getText().isEmpty()) {

                        jTextField40senha.setForeground(Color.red);
                        jTextField40senha.setText("Obrigatório");

                    } else if (jFormattedTextField3.getText() == "- -") {

                        jFormattedTextField3.setForeground(Color.red);

                    } else if (jFormattedTextField2.getText() == "(+258)") {

                        jFormattedTextField2.setForeground(Color.red);

                    }

                } else {
                    int pergunta = JOptionPane.showConfirmDialog(rootPane, "Acualizar dados do Funcionário?", "Actualização de dados", JOptionPane.YES_NO_OPTION);

                    if (pergunta == JOptionPane.YES_OPTION) {
                        int actua = pst.executeUpdate();

                        if (actua > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Dados do Funcionário actualizados com sucesso!");

                            jTextFieldcod.setText(null);
                            jTextField18name.setText(null);
                            jTextField19nuit.setText(null);
                            jComboBox1.setSelectedIndex(0);
                            jComboBox2.setSelectedIndex(0);
                            jFormattedTextField1.setText(null);
                            jTextField23enail.setText(null);
                            jFormattedTextField3.setText(null);
                            jTextField44uso.setText(null);
                            jTextField40senha.setText(null);
                            jTextFieldcod.setText(null);
                            jFormattedTextField2.setText(null);

                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Actualizacao nao sucedida!");

                        }
                    }

                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Insira o codigo do Funcionario");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    void limparFunc() {

        jTextFieldcod.setText(null);
        jTextField18name.setText(null);
        jTextField19nuit.setText(null);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jFormattedTextField1.setText(null);
        jTextField23enail.setText(null);
        jFormattedTextField3.setText(null);
        jTextField44uso.setText(null);
        jTextField40senha.setText(null);
        jTextFieldcod.setText(null);
        jFormattedTextField2.setText(null);

        JOptionPane.showMessageDialog(rootPane, "Campos limpos");

    }

    void eliminarFunc() {

        String eliminar = "delete from Funcionario where cod = ?";

        try {

            if (!"".equals(jTextFieldcod.getText())) {

                String id = jTextFieldcod.getText();

                pst = conexao.prepareStatement(eliminar);

                pst.setString(1, id);

                int per = JOptionPane.showConfirmDialog(rootPane, "Eliminar Funcionário?", "Eliminação", JOptionPane.YES_NO_OPTION);

                if (per == JOptionPane.YES_OPTION) {
                    int ap = pst.executeUpdate();

                    if (ap > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Dados do Funcionário eliminados com sucesso!");

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Eliminação nao sucedida!");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Insira o codigo do Funcionário");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }

    //=-=-=-= TITULAR =-=-=-= TITULAR =-=-=-= TITULAR =-=-=-=
    void salvarTitular() {

        String salvar = "insert into titular(cod, nome, distrito, bairro, telefone, email, site, cod_rep, nuit) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            pst = conexao.prepareStatement(salvar);

            pst.setString(1, jTextField2cod.getText());
            pst.setString(2, jTextField26nome.getText());
            pst.setString(3, jComboBox5dist.getSelectedItem().toString());
            pst.setString(4, jTextField31bairr.getText());
            pst.setString(5, jFormattedTextField4tel.getText());
            pst.setString(6, jTextField27email.getText());
            pst.setString(7, jTextField30site.getText());
            pst.setString(8, jTextField1codre.getText());

            pst.setString(9, jTextField28nuit.getText());

            if (jTextField2cod.getText().isEmpty() || jTextField26nome.getText().isEmpty() || jFormattedTextField4tel.getText() == "(+258)" || jTextField1codre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                if (jTextField2cod.getText().isEmpty()) {

                    jTextField2cod.setForeground(Color.red);
                    jTextField2cod.setText("Muito Obrigatório");

                } else if (jTextField26nome.getText().isEmpty()) {

                    jTextField26nome.setForeground(Color.red);
                    jTextField26nome.setText("Obrigatório");

                } else if (jTextField1codre.getText().isEmpty()) {

                    jTextField1codre.setForeground(Color.red);
                    jTextField1codre.setText("Obrigatório");

                } else if (jFormattedTextField4tel.getText().isEmpty()) {

                    jFormattedTextField4tel.setForeground(Color.red);
                    //jTextField1codre.setText("Obrigatório");

                }

            } else {
                int ad = JOptionPane.showConfirmDialog(rootPane, "Cadastrar novo Titular?", "Cadastro", JOptionPane.YES_NO_OPTION);
                if (ad == JOptionPane.YES_OPTION) {
                    int execute = pst.executeUpdate();
                    if (execute > 0) {
                        JOptionPane.showMessageDialog(null, "Titular cadastrado com sucesso");

                        jTextField2cod.setText(null);
                        jTextField26nome.setText(null);
                        jTextField31bairr.setText(null);

                        jTextField28nuit.setText(null);
                        jFormattedTextField4tel.setText(null);
                        jTextField27email.setText(null);
                        jTextField30site.setText(null);
                        jTextField1codre.setText(null);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro não sucedido");
                    }
                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    void editarTitular() {

        String editar = "update titular set cod = ?, nome = ?, distrito = ?, bairro = ?, telefone = ?, email = ?, site = ?, cod_rep = ?, nuit = ? where cod = ?";

        try {

            if (!"".equals(jTextField2cod.getText())) {

                pst = conexao.prepareStatement(editar);

                pst.setString(1, jTextField2cod.getText());
                pst.setString(2, jTextField26nome.getText());
                pst.setString(3, jComboBox5dist.getSelectedItem().toString());
                pst.setString(4, jTextField31bairr.getText());
                pst.setString(5, jFormattedTextField4tel.getText());
                pst.setString(6, jTextField27email.getText());
                pst.setString(7, jTextField30site.getText());
                pst.setString(8, jTextField1codre.getText());
                pst.setString(9, jTextField28nuit.getText());

                pst.setString(10, jTextField2cod.getText());

                if (jTextField2cod.getText().isEmpty() || jTextField26nome.getText().isEmpty() || jFormattedTextField4tel.getText() == "(+258)" || jTextField1codre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                    if (jTextField2cod.getText().isEmpty()) {

                        jTextField2cod.setForeground(Color.red);
                        jTextField2cod.setText("Muito Obrigatório");

                    } else if (jTextField26nome.getText().isEmpty()) {

                        jTextField26nome.setForeground(Color.red);
                        jTextField26nome.setText("Obrigatório");

                    } else if (jTextField1codre.getText().isEmpty()) {

                        jTextField1codre.setForeground(Color.red);
                        jTextField1codre.setText("Obrigatório");

                    } else if (jFormattedTextField4tel.getText().isEmpty()) {

                        jFormattedTextField4tel.setForeground(Color.red);
                        //jTextField1codre.setText("Obrigatório");

                    }

                } else {
                    int pergunta = JOptionPane.showConfirmDialog(rootPane, "Acualizar dados do Titular?", "Actualização de dados", JOptionPane.YES_NO_OPTION);

                    if (pergunta == JOptionPane.YES_OPTION) {
                        int actua = pst.executeUpdate();

                        if (actua > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Dados do Titular actualizados com sucesso!");

                            jTextField2cod.setText(null);
                            jTextField26nome.setText(null);
                            jTextField31bairr.setText(null);
                            jFormattedTextField4tel.setText(null);
                            jTextField27email.setText(null);
                            jTextField28nuit.setText(null);
                            jTextField30site.setText(null);
                            jTextField1codre.setText(null);

                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Actualizacao nao sucedida!");

                        }
                    }

                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Insira o codigo do Titular");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    void apagarTitular() {

        String apagar = "delete from titular where cod = ?";

        try {

            if (!"".equals(jTextField2cod.getText())) {

                String id = jTextField2cod.getText();

                pst = conexao.prepareStatement(apagar);

                pst.setString(1, id);

                int per = JOptionPane.showConfirmDialog(rootPane, "Eliminar Titular?", "Eliminação", JOptionPane.YES_NO_OPTION);

                if (per == JOptionPane.YES_OPTION) {
                    int ap = pst.executeUpdate();

                    if (ap > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Dados do Titular eliminados com sucesso!");

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Eliminação nao sucedida!");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Insira o codigo do Titular");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }

    void limparTitular() {

        jTextField2cod.setText(null);
        jTextField26nome.setText(null);
        jTextField31bairr.setText(null);
        jFormattedTextField4tel.setText(null);
        jTextField27email.setText(null);
        jTextField28nuit.setText(null);
        jTextField30site.setText(null);
        jTextField1codre.setText(null);

        JOptionPane.showMessageDialog(rootPane, "Todos os campos limpos");
    }

    //=-=-=-= REPRESENTANTE =-=-=-= REPRESENTANTE =-=-=-= REPRESENTANTE =-=-=-=
    void salvarRepresentante() {

        String salvar = "insert into representante (cod, nome, bi, residencia, email, celular) values(?, ?, ?, ?, ?, ?)";

        try {

            pst = conexao.prepareStatement(salvar);

            pst.setString(1, jTextField38cod.getText());
            pst.setString(2, jTextField33nome.getText());
            pst.setString(3, jTextField35bi.getText());
            pst.setString(4, jTextField36resi.getText());
            pst.setString(5, jTextField34email.getText());
            pst.setString(6, jFormattedTextField4tel1.getText());

            if (jTextField38cod.getText().isEmpty() || jTextField33nome.getText().isEmpty() || jFormattedTextField4tel1.getText() == "(+258)") {

                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                if (jTextField38cod.getText().isEmpty()) {

                    jTextField38cod.setForeground(Color.red);
                    jTextField38cod.setText("Muito Obrigatório");

                } else if (jTextField33nome.getText().isEmpty()) {

                    jTextField33nome.setForeground(Color.red);
                    jTextField33nome.setText("Obrigatório");

                } else if (jFormattedTextField4tel1.getText().equals("(+258)")) {

                    jFormattedTextField4tel1.setForeground(Color.red);

                }

            } else {
                int ad = JOptionPane.showConfirmDialog(rootPane, "Cadastrar novo Representante?", "Cadastro", JOptionPane.YES_NO_OPTION);
                if (ad == JOptionPane.YES_OPTION) {
                    int execute = pst.executeUpdate();
                    if (execute > 0) {
                        JOptionPane.showMessageDialog(null, "Representante cadastrado com sucesso");

                        jTextField38cod.setText(null);
                        jTextField33nome.setText(null);
                        jTextField35bi.setText(null);
                        jTextField36resi.setText(null);
                        jTextField34email.setText(null);
                        jFormattedTextField4tel1.setText(null);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro não sucedido");
                    }
                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    void editarRepresentante() {

        String editar = "update representante set cod = ?, nome = ?, bi = ?, residencia = ?,  email = ?, celular = ? where cod = ?";

        try {

            pst = conexao.prepareStatement(editar);

            pst.setString(1, jTextField38cod.getText());
            pst.setString(2, jTextField33nome.getText());
            pst.setString(3, jTextField35bi.getText());
            pst.setString(4, jTextField36resi.getText());
            pst.setString(5, jTextField34email.getText());
            pst.setString(6, jFormattedTextField4tel1.getText());
            pst.setString(7, jTextField38cod.getText());

            if (jTextField38cod.getText().isEmpty() || jTextField33nome.getText().isEmpty() || jFormattedTextField4tel1.getText() == "(+258)") {

                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                if (jTextField38cod.getText().isEmpty()) {

                    jTextField38cod.setForeground(Color.red);
                    jTextField38cod.setText("Muito Obrigatório");

                } else if (jTextField33nome.getText().isEmpty()) {

                    jTextField33nome.setForeground(Color.red);
                    jTextField33nome.setText("Obrigatório");

                } else if (jFormattedTextField4tel1.getText().equals("(+258)")) {

                    jFormattedTextField4tel1.setForeground(Color.red);

                }

            } else {
                int pergunta = JOptionPane.showConfirmDialog(rootPane, "Acualizar dados do Representante?", "Actualização de dados", JOptionPane.YES_NO_OPTION);

                if (pergunta == JOptionPane.YES_OPTION) {
                    int actua = pst.executeUpdate();

                    if (actua > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Dados do Representante actualizados com sucesso!");

                        jTextField38cod.setText(null);
                        jTextField33nome.setText(null);
                        jTextField35bi.setText(null);
                        jTextField36resi.setText(null);
                        jTextField34email.setText(null);
                        jFormattedTextField4tel1.setText(null);

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Actualizacao nao sucedida!");

                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    void apagarRepresentante() {

        String apagar = "delete from representante where cod = ?";

        try {

            if (!"".equals(jTextField38cod.getText())) {

                String id = jTextField38cod.getText();

                pst = conexao.prepareStatement(apagar);

                pst.setString(1, id);

                int per = JOptionPane.showConfirmDialog(rootPane, "Eliminar Representante?", "Eliminação", JOptionPane.YES_NO_OPTION);

                if (per == JOptionPane.YES_OPTION) {
                    int ap = pst.executeUpdate();

                    if (ap > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Dados do Representante eliminados com sucesso!");

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Eliminação nao sucedida!");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Insira o codigo do Representante");
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    void limparRepresentante() {

        jTextField38cod.setText(null);
        jTextField33nome.setText(null);
        jTextField35bi.setText(null);
        jTextField36resi.setText(null);
        jTextField34email.setText(null);
        jFormattedTextField4tel1.setText(null);

        JOptionPane.showMessageDialog(rootPane, "Todos os campos limpos");

    }

    //=-=-=-= FONTE =-=-=-= FONTE =-=-=-= FONTE =-=-=-=
    void salvarFonte() {

        String salvar = "insert into fonte (cod, tipo_id, nr_licenca, nr_recibo, data_pag, distrito, bairro, potencia, latitude, longitude) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            pst = conexao.prepareStatement(salvar);

            pst.setString(1, jTextField7.getText());
            pst.setString(2, String.valueOf(jComboBox4.getSelectedIndex() + 1));
            pst.setString(3, jTextField4.getText());
            pst.setString(4, jTextField41.getText());
            pst.setString(5, jFormattedTextField1.getText());
            pst.setString(6, jComboBox5dist1.getSelectedItem().toString());
            pst.setString(7, jTextField42.getText());
            pst.setString(8, jTextField32.getText());
            pst.setString(9, jTextField5.getText());
            pst.setString(10, jTextField6.getText());

            if (jTextField7.getText().isEmpty() || jTextField4.getText().isEmpty() || jTextField41.getText().isEmpty() || jTextField42.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                if (jTextField7.getText().isEmpty()) {

                    jTextField7.setForeground(Color.red);
                    jTextField7.setText("Muito Obrigatório");

                } else if (jTextField4.getText().isEmpty()) {

                    jTextField4.setForeground(Color.red);
                    jTextField4.setText("Obrigatório");

                } else if (jTextField41.getText().isEmpty()) {

                    jTextField41.setForeground(Color.red);
                    jTextField41.setText("Obrigatório");

                } else if (jTextField42.getText().isEmpty()) {

                    jTextField42.setForeground(Color.red);
                    jTextField42.setText("Obrigatório");

                }

            } else {
                int ad = JOptionPane.showConfirmDialog(rootPane, "Registar nova Fonte?", "Registar", JOptionPane.YES_NO_OPTION);
                if (ad == JOptionPane.YES_OPTION) {
                    int execute = pst.executeUpdate();
                    if (execute > 0) {
                        JOptionPane.showMessageDialog(null, "Fonte registada com sucesso");

                        jTextField7.setText(null);
                        jTextField4.setText(null);
                        jTextField41.setText(null);
                        jFormattedTextField1.setText(null);
                        jTextField42.setText(null);
                        jTextField32.setText(null);
                        jTextField5.setText(null);
                        jTextField6.setText(null);

                    } else {
                        JOptionPane.showMessageDialog(null, "Registo não sucedido");
                    }
                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    void editarFonte() {

        String editar = "update fonte set cod = ?, tipo_id = ?, nr_licenca = ?, nr_recibo = ?, data_pag = ?, distrito = ?, bairro = ?, potencia = ?, latitude = ?, longitude = ? where cod = ?";

        try {

            pst = conexao.prepareStatement(editar);

            pst.setString(1, jTextField7.getText());
            pst.setString(2, String.valueOf(jComboBox4.getSelectedIndex() + 1));
            pst.setString(3, jTextField4.getText());
            pst.setString(4, jTextField41.getText());
            pst.setString(5, jFormattedTextField1.getText());
            pst.setString(6, jComboBox5dist1.getSelectedItem().toString());
            pst.setString(7, jTextField42.getText());
            pst.setString(8, jTextField32.getText());
            pst.setString(9, jTextField5.getText());
            pst.setString(10, jTextField6.getText());

            if (jTextField7.getText().isEmpty() || jTextField4.getText().isEmpty() || jTextField41.getText().isEmpty() || jTextField42.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

                if (jTextField7.getText().isEmpty()) {

                    jTextField7.setForeground(Color.red);
                    jTextField7.setText("Muito Obrigatório");

                } else if (jTextField4.getText().isEmpty()) {

                    jTextField4.setForeground(Color.red);
                    jTextField4.setText("Obrigatório");

                } else if (jTextField41.getText().isEmpty()) {

                    jTextField41.setForeground(Color.red);
                    jTextField41.setText("Obrigatório");

                } else if (jTextField42.getText().isEmpty()) {

                    jTextField42.setForeground(Color.red);
                    jTextField42.setText("Obrigatório");

                }

            } else {
                int pergunta = JOptionPane.showConfirmDialog(rootPane, "Actualizar dados da Fonte?", "Actualização de dados", JOptionPane.YES_NO_OPTION);

                if (pergunta == JOptionPane.YES_OPTION) {
                    int actua = pst.executeUpdate();

                    if (actua > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Dados da Fonte actualizados com sucesso!");

                        jTextField7.setText(null);
                        jTextField4.setText(null);
                        jTextField41.setText(null);
                        jFormattedTextField1.setText(null);
                        jTextField42.setText(null);
                        jTextField32.setText(null);
                        jTextField5.setText(null);
                        jTextField6.setText(null);

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Actualização nao sucedida!");

                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    void apagarFonte() {

        String apagar = "delete from Fonte where cod = ?";

        try {

            if (!"".equals(jTextField7.getText())) {

                String id = jTextField7.getText();

                pst = conexao.prepareStatement(apagar);

                pst.setString(1, id);

                int per = JOptionPane.showConfirmDialog(rootPane, "Eliminar Fonte?", "Eliminação", JOptionPane.YES_NO_OPTION);

                if (per == JOptionPane.YES_OPTION) {
                    int ap = pst.executeUpdate();

                    if (ap > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Fonte eliminada com sucesso!");

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Eliminação nao sucedida!");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Insira o codigo da Fonte");
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    void limparFonte() {

        jTextField7.setText(null);
        jTextField4.setText(null);
        jTextField41.setText(null);
        jFormattedTextField1.setText(null);
        jTextField42.setText(null);
        jTextField32.setText(null);
        jTextField5.setText(null);
        jTextField6.setText(null);

        JOptionPane.showMessageDialog(rootPane, "Todos os campos limpos");

    }

    void inserirTitularFonte() {

        //metodo para atribuit relacao entre o titular e a fonte
        String cmd = "insert into fontextitular (cod_f, cod_t) values(?, ?)";
        int rowf = jTable1.getSelectedRow();
        int rowt = jTable2.getSelectedRow();

        try {
            //System.out.println("Titular = " + rowt + "\nFonte = " + rowf);
            //System.out.println(String.valueOf(jTable1.getModel().getValueAt(rowf, 0)));
            if (rowf > -1 && rowt > -1) {

                pst = conexao.prepareStatement(cmd);

                pst.setString(1, String.valueOf(jTable1.getModel().getValueAt(rowf, 0)));
                pst.setString(2, String.valueOf(jTable1.getModel().getValueAt(rowt, 0)));

                int per = JOptionPane.showConfirmDialog(rootPane, "Atribuir fonte " + jTable1.getModel().getValueAt(rowf, 1) + " ao Titular " + jTable2.getModel().getValueAt(rowt, 1) + "?", "Atribuição de fonte", JOptionPane.YES_NO_OPTION);

                if (per == JOptionPane.YES_OPTION) {
                    int exe = pst.executeUpdate();

                    if (exe > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Fonte atribuida com sucesso");
                    } else {

                        JOptionPane.showMessageDialog(rootPane, "Atribuição da fonte não sucedida!");
                    }

                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Selecione nas tabelas o titular e a fonte");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField26nome = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField27email = new javax.swing.JTextField();
        jTextField28nuit = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField30site = new javax.swing.JTextField();
        jTextField31bairr = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField2cod = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField1codre = new javax.swing.JTextField();
        jComboBox5dist = new javax.swing.JComboBox();
        jFormattedTextField4tel = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField33nome = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField34email = new javax.swing.JTextField();
        jTextField35bi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField36resi = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField38cod = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jFormattedTextField4tel1 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jComboBox5dist1 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField18name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField19nuit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField23enail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldcod = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jTextField40senha = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField44uso = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jButton26 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 255));

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 0, 255));
        jButton13.setText("Editar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(204, 204, 204));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 0, 255));
        jButton15.setText("Salvar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Telefone:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nome:");

        jTextField26nome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField26nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26nomeActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Email:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("NUIT:");

        jTextField27email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField28nuit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField28nuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28nuitActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Distrito:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Site:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Bairro/Av");

        jTextField30site.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField30site.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30siteActionPerformed(evt);
            }
        });

        jTextField31bairr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField31bairr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31bairrActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Codigo:");

        jTextField2cod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton22.setBackground(new java.awt.Color(204, 204, 204));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton22.setForeground(new java.awt.Color(0, 0, 255));
        jButton22.setText("Apagar");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(204, 204, 204));
        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton25.setForeground(new java.awt.Color(0, 0, 255));
        jButton25.setText("Limpar");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("TITULAR");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Código do representante:");

        jTextField1codre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1codre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1codreActionPerformed(evt);
            }
        });

        jComboBox5dist.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox5dist.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tete", "Angónia", "Cahora-Bassa", "Changara", "Chifunde", "Chiuta", "Doa", "Macanga", "Magoé", "Marara", "Marária", "Moatize", "Mutarara", "Tsangano", "Zumbo" }));
        jComboBox5dist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5distActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField4tel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(+258) ## ### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField4tel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField4tel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jFormattedTextField4tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField4telActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2))
                        .addContainerGap())
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 209, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel15))
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1codre))
                                    .addComponent(jTextField27email)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField26nome, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2cod, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField28nuit)
                                    .addComponent(jTextField31bairr)
                                    .addComponent(jTextField30site)
                                    .addComponent(jComboBox5dist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField4tel, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(193, 193, 193))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton22)
                                .addGap(219, 219, 219))))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField26nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField2cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField28nuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox5dist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField31bairr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField4tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField27email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField30site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField1codre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton13)
                    .addComponent(jButton22)
                    .addComponent(jButton25))
                .addGap(25, 25, 25))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox5dist, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel38, jLabel9, jTextField1codre, jTextField26nome, jTextField27email, jTextField28nuit, jTextField2cod, jTextField30site, jTextField31bairr});

        jTabbedPane1.addTab("Titular", jPanel2);

        jButton16.setBackground(new java.awt.Color(204, 204, 204));
        jButton16.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(0, 0, 204));
        jButton16.setText("Salvar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(204, 204, 204));
        jButton18.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(0, 0, 204));
        jButton18.setText("Limpar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Nome:");

        jTextField33nome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField33nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField33nomeActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Email:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("BI:");

        jTextField34email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField35bi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField35bi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField35biActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Residencia:");

        jTextField36resi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField36resi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField36resiActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Celular:");

        jButton23.setBackground(new java.awt.Color(204, 204, 204));
        jButton23.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton23.setForeground(new java.awt.Color(0, 0, 204));
        jButton23.setText("Apagar");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("REPRESENTANTE");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Código:");

        jTextField38cod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField38cod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField38codActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(204, 204, 204));
        jButton17.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(0, 0, 204));
        jButton17.setText("Editar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField4tel1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(+258) ## ### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField4tel1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField4tel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jFormattedTextField4tel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField4tel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                            .addComponent(jSeparator4)
                            .addComponent(jSeparator3))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField35bi, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField34email, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jFormattedTextField4tel1))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField36resi, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField38cod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextField33nome, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(206, 206, 206))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16)
                                .addGap(5, 5, 5)
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton23)
                                .addGap(210, 210, 210))))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel18, jLabel19, jLabel20, jLabel21, jLabel22});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField38cod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jTextField33nome))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField35bi)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jTextField36resi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField34email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField4tel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton16)
                    .addComponent(jButton23)
                    .addComponent(jButton17))
                .addGap(29, 29, 29))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel18, jLabel19, jLabel20, jLabel21, jLabel22});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextField4tel1, jTextField33nome, jTextField34email, jTextField35bi, jTextField36resi});

        jTabbedPane1.addTab("Representante", jPanel3);

        jButton19.setBackground(new java.awt.Color(204, 204, 204));
        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 0, 255));
        jButton19.setText("Editar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(204, 204, 204));
        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton20.setForeground(new java.awt.Color(0, 0, 255));
        jButton20.setText("Limpar");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(204, 204, 204));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton21.setForeground(new java.awt.Color(0, 0, 255));
        jButton21.setText("Salvar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Data de Pagamento:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Tipo:");

        jTextField32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Distrito:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Potencia(KVA):");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Nr. Licenca:");

        jTextField41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField41ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Bairro/Av.");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Nr. Recibo:");

        jTextField42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField42ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Categoria:");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Latitude:");

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Longitude:");

        jButton24.setBackground(new java.awt.Color(204, 204, 204));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(0, 0, 255));
        jButton24.setText("Apagar");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("DADOS DA FONTE");

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2ª Categoria", "3ª Categoria", "4ª Categoria" }));
        jComboBox3.setEnabled(false);
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Transformador", "Gerador", "Vedação eléctrica" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Código:");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox5dist1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox5dist1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tete", "Angónia", "Cahora-Bassa", "Changara", "Chifunde", "Chiuta", "Doa", "Macanga", "Magoé", "Marara", "Marária", "Moatize", "Mutarara", "Tsangano", "Zumbo" }));
        jComboBox5dist1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5dist1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 255));
        jButton1.setText("Atribuir fonte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 51, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("TITULARES");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 51, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("FONTES");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(334, 334, 334))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(jButton20)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButton21)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButton19)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButton24)
                                                        .addGap(28, 28, 28))
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(10, 10, 10)
                                                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(18, 18, 18)))
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(10, 10, 10))
                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(13, 13, 13)))
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jTextField7)
                                                            .addComponent(jComboBox3, 0, 137, Short.MAX_VALUE)))
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(13, 13, 13)
                                                        .addComponent(jComboBox5dist1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(7, 7, 7)
                                                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                                    .addComponent(jTextField5))))
                                        .addGap(0, 9, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox5dist1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton19)
                    .addComponent(jButton24))
                .addGap(28, 28, 28))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox3, jFormattedTextField1, jLabel17, jLabel23, jLabel24, jLabel25, jLabel26, jLabel27, jLabel28, jLabel29, jLabel30, jLabel31, jTextField32, jTextField4, jTextField41, jTextField42, jTextField5, jTextField6});

        jTabbedPane1.addTab("Dados da Fonte", jPanel4);

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 255));
        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 255));
        jButton5.setText("Editar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(204, 204, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 255));
        jButton6.setText("Apagar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome:");

        jTextField18name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField18name.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NUIT:");

        jTextField19nuit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField19nuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Departamento:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Funcao/Categoria:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Data de Nomeacao:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Email:");

        jTextField23enail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField23enail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23enail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23enailActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Celular");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Codigo:");

        jTextFieldcod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldcod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldcodActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("DADOS DO FUNCIONARIO");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Senha:");

        jTextField40senha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField40senha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField40senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField40senhaActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Usuário:");

        jTextField44uso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField44uso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField44uso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44usoActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Energia", "Recursos Humanos" }));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Engenheiro Electrotécnico", "Engenheiro de Recursos Minerais", "Técnico profissional", "Assistente técnico " }));

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(+258) ## ### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton26.setBackground(new java.awt.Color(204, 204, 204));
        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton26.setForeground(new java.awt.Color(0, 0, 255));
        jButton26.setText("Limpar");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator8)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField44uso, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldcod, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField18name, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField19nuit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField23enail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField40senha, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(176, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(226, 226, 226))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox2, jTextField19nuit});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField19nuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField23enail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jTextField40senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(jTextField44uso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton26))
                .addGap(20, 20, 20))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox2, jFormattedTextField2, jFormattedTextField3, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jTextField18name, jTextField19nuit, jTextField23enail, jTextFieldcod});

        jTabbedPane1.addTab("Dados do Funcionario", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        limparTitular();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTextField26nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26nomeActionPerformed

    private void jTextField28nuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28nuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField28nuitActionPerformed

    private void jTextField31bairrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31bairrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31bairrActionPerformed

    private void jTextField30siteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField30siteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30siteActionPerformed

    private void jTextField36resiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField36resiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField36resiActionPerformed

    private void jTextField33nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField33nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField33nomeActionPerformed

    private void jTextField35biActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField35biActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField35biActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField41ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextFieldcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldcodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldcodActionPerformed

    private void jTextField40senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField40senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField40senhaActionPerformed

    private void jTextField44usoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44usoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44usoActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        comboBox();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jTextField23enailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23enailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23enailActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        registarFunc();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        actualizarFunc();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        eliminarFunc();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1codreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1codreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1codreActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        limparFunc();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        editarTitular();

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        salvarTitular();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox5distActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5distActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5distActionPerformed

    private void jFormattedTextField4telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4telActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4telActionPerformed

    private void jTextField38codActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField38codActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38codActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        apagarTitular();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jFormattedTextField4tel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4tel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4tel1ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        limparRepresentante();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        salvarRepresentante();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        editarRepresentante();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        apagarRepresentante();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jComboBox5dist1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5dist1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5dist1ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        editarFonte();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        limparFonte();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        salvarFonte();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        apagarFonte();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTextField42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField42ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        inserirTitularFonte();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormFonteEnergetica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFonteEnergetica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFonteEnergetica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFonteEnergetica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFonteEnergetica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5dist;
    private javax.swing.JComboBox jComboBox5dist1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4tel;
    private javax.swing.JFormattedTextField jFormattedTextField4tel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField18name;
    private javax.swing.JTextField jTextField19nuit;
    private javax.swing.JTextField jTextField1codre;
    private javax.swing.JTextField jTextField23enail;
    private javax.swing.JTextField jTextField26nome;
    private javax.swing.JTextField jTextField27email;
    private javax.swing.JTextField jTextField28nuit;
    private javax.swing.JTextField jTextField2cod;
    private javax.swing.JTextField jTextField30site;
    private javax.swing.JTextField jTextField31bairr;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33nome;
    private javax.swing.JTextField jTextField34email;
    private javax.swing.JTextField jTextField35bi;
    private javax.swing.JTextField jTextField36resi;
    private javax.swing.JTextField jTextField38cod;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40senha;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField44uso;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextFieldcod;
    // End of variables declaration//GEN-END:variables
}

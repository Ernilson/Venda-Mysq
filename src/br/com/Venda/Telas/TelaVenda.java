/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Venda.Telas;

import br.com.Produto.Model.Carrinho;
import br.com.Venda.DAL.ModuloConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import br.com.Venda.Model.vendaBLL;
import br.com.Produto.Model.ProdutoBLL;
import br.com.Utils.ModeloTabela;
import static br.com.Venda.Telas.TPagamento.CboCont;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javazoom.jl.player.Player;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaVenda extends javax.swing.JFrame {

    float ttx;
    int fre;
    int flag = 1;
    float total;
    float ttext;
    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Metodo para preencher com os dados do banco da tabela "cadastro" o Jtable - VendTbale;
    public void pesquisaCadastro() {
        conexao.conector();
        String sql = "select nome as Nome, estatus as Status from clientes where nome like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, VendNome.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                          
            VendTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void actionPerformed() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("C:\\Windows\\system32\\calc.exe");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public void actionPerformed2() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("C:\\Windows\\system32\\notepad.exe");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    private void PesquisaPegaPedido() {
        conexao.conector();
        String sql = "update venda set descricao=?, qtdp=?, valor_total=?, forma_pg=? where id_v=?"; // string para habilitar os set's
        try {
            pst = conexao.conn.prepareStatement(sql);
           // pst.setString(1, ResultPesq.getText());
            pst.setString(1, ProdDesc.getText());            
            pst.setString(2, ProdQtd.getText());
            pst.setString(3, ProdPreco.getText());
            pst.setString(4, ProdPag.getText());    //metodo para setar mostrar o que vem do banco
            pst.setString(5, ResultId.getText());
//            if ((ResultPesq.getText().isEmpty()) || (ProdDesc.getText().isEmpty())) {
//                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
//            } else {
                pst.executeUpdate();

//                if (altera > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterado com sucesso!");

//                }
//            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void Bloc() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("C:\\Windows\\system32\\notepad.exe");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line = null;

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    //Metodo para preencher com os dados do banco da tabela "cadastro" o Jtable - VendTbale;
    private void setarCadastro() {
        int setar = VendTable.getSelectedRow();
        //VendCod.setText(VendTable.getModel().getValueAt(setar, ).toString());
        VendNome.setText(VendTable.getModel().getValueAt(setar, 0).toString());
        //a linha a baixo desabilita o butao adicionar
        //   btnSalvar.setEnabled(false);       
    }

    //Metodo para preencher com os dados do banco da tabela "Produto" o Jtable - VendTbale2 na primeira Aba;
    public void pesquisaProdutos() {
        conexao.conector();
        String sql = "select descricao_p as Descrição, qtd as Qtd, preco as Preço, dataq as Data from produtos where descricao_p like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, VendProduto.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar
            VendTable2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados da tabela Produtos o JTable "VendTable2" na primeira Aba;
    private void setarProduto() {
        int setar = VendTable2.getSelectedRow();
        VendProduto.setText(VendTable2.getModel().getValueAt(setar, 0).toString());
        VendQtd.setText(VendTable2.getModel().getValueAt(setar, 1).toString());
        VendValorItem.setText(VendTable2.getModel().getValueAt(setar, 2).toString());        
        VendValor.setText(VendTable2.getModel().getValueAt(setar, 2).toString());
        VendData.setText(VendTable2.getModel().getValueAt(setar, 3).toString());
    }

    //Metodo para preencher com os dados do banco da tabela "venda" o Jtable - ReultTbale na segunda Aba;
    public void pesquisaVenda(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                           
            ResultTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void pesquisaCarrinho(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                           
            VendTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados do JTable "ResultTable" na segunda Aba;
    private void setarVenda() {
        int setar = ResultTable.getSelectedRow();
        ResultId.setText(ResultTable.getModel().getValueAt(setar, 0).toString());
        ResultPesq.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
        ProdDesc.setText(ResultTable.getModel().getValueAt(setar, 2).toString());
        ProdQtd.setText(ResultTable.getModel().getValueAt(setar, 4).toString());
        ProdPreco.setText(ResultTable.getModel().getValueAt(setar, 3).toString());
        ProdPag.setText(ResultTable.getModel().getValueAt(setar, 6).toString());
    }

    //Metodo para preencher com os dados do banco da tabela "produtos" o Jtable - ReultTbale na segunda Aba;
    public void pesquisaProduto(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                  
            ResultTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Tentativa_A(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                  
            tblFechamento.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void Carrinho() {
        ModuloConexao conexao = new ModuloConexao();
        String sql = "insert into carrinho (nome, descricao, qtdp,valor, forma_pg) select nome, descricao, qtdp,valor, forma_pg from carrinho; ";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Por favor Adicione a Venda antes finalizar! ");
        }
    }
    // Metodo para limpar a tabela carrino no banco

    private void excluir() {
        String sql = "truncate carrinho;";
        try {
            pst = conexao.conn.prepareStatement(sql);
            int apagado = pst.executeUpdate();
            if (apagado > 0) {
                // JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

        }
    }
        
//     public void som() {
//        try {
//            File file = new File("C:\\Users\\Ernilson\\Documents\\NetBeansProjects\\ProjetoVenda-3.0\\src\\br\\com\\Audio\\SonyPlay1.mp3");
//            FileInputStream fis = new FileInputStream(file);
//            BufferedInputStream bu = new BufferedInputStream(fis);
//
//            try {
//                Player player = new Player(bu);
//                player.play();
//            } catch (Exception e) {
//                System.out.println("Erro ao tentar reproduzir o som" + e.getMessage());
//            }
//
//        } catch (Exception e) {
//            System.out.println("Erro ao tentar reproduzir o som" + e.getMessage());
//        }
//    }
    

// Método para visualizar a tabela 3 da primeira aba
    private void VisualiaCarrinho(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                  
            VendTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo para calcular o valor total da tabela venda 
    public void Leandro() {
        ttext = Float.parseFloat(ValorFinal.getText());
        total = Float.parseFloat(VendValorT.getText());
        float subtotal = Float.parseFloat(ValorFinal.getText());
        float qtd = Float.parseFloat(VendQtd.getText());
        float Final = (float) (total + subtotal);
        total = Final;
        ValorFinal.setText(String.valueOf(total));

    }

    //Metodo para preencher os campos de texto do formulario com os dados da tabela venda o JTable "ResultTable" na segunda Aba;
    private void setarReult() {
        int setar = ResultTable.getSelectedRow();
        ResultId.setText(ResultTable.getModel().getValueAt(setar, 0).toString());
        ResultPesq.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
        ProdDesc.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
        ProdQtd.setText(ResultTable.getModel().getValueAt(setar, 2).toString());
        ProdPreco.setText(ResultTable.getModel().getValueAt(setar, 3).toString());
    }

    //Metodo para preencher os campos de texto do formulario (primeira aba) com os dados da tabela Carrinho;
    private void avançar() {
        int setar = VendTable3.getSelectedRow();
        VendNome.setText(VendTable3.getModel().getValueAt(setar, 0).toString());
        VendProduto.setText(VendTable3.getModel().getValueAt(setar, 1).toString());
        VendQtd.setText(VendTable3.getModel().getValueAt(setar, 2).toString());
        VendValorItem.setText(VendTable3.getModel().getValueAt(setar, 3).toString());
    }

    private void LimparEstoque() {
        ResultId.setText(null);
        ResultPesq.setText(null);
        ResultTable.setVisible(false);
        ProdDesc.setText(null);
        ProdQtd.setText(null);
        ProdPreco.setText(null);
        ProdPag.setText(null);
        //BtnProdAlterar.setText(null);
    }

    private void limpaTela() {
        // VendNome.setText("");
        VendTable.setVisible(false);
        VendProduto.setText("");
        VendTable2.setVisible(false);
        VendQtd.setText("");
        VendValorT.setText("");                // Metodo para limapr campos;
        VendValorItem.setText("");
        VendData.setText("");
    }

    private void limpaTela2() {
        VendNome.setText("");
        VendTable.setVisible(false);
        VendProduto.setText("");
        VendTable2.setVisible(false);
        VendQtd.setText("");
        VendValorT.setText("");                // Metodo para limapr campos;
        VendValorItem.setText("");
        VendData.setText("");
    }

    private void limpaProdutos() {
        ProdDesc.setText(null);
        ProdQtd.setText(null);
        ProdPreco.setText(null);
    }

    /**
     * Creates new form TelaVenda select sum(valor) as valor from carrinho;
     */
    public TelaVenda() {
        initComponents();
        ValorFinal.setText("0");
        VendQtd.setEnabled(false);
        VendProduto.setEnabled(false);
        VendAdd.setEnabled(false);

    }
    vendaBLL bll = new vendaBLL();
    ProdutoBLL bl2 = new ProdutoBLL();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jSpinner1 = new javax.swing.JSpinner();
        jSlider1 = new javax.swing.JSlider();
        Vendas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        VendTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        VendNome = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        VendTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        VendData = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        VendTable3 = new javax.swing.JTable();
        LblBemVindo = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        VendAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        ValorFinal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnFinal = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        VendQtd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        VendValorT = new javax.swing.JTextField();
        VendValorItem = new javax.swing.JTextField();
        VendProduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        VendValor = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ResultPesq = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ResultId = new javax.swing.JTextField();
        BtnVendido = new javax.swing.JButton();
        ResultEstoque = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        BtnAdic = new javax.swing.JButton();
        ProdDEL = new javax.swing.JButton();
        BtnApagaP = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ProdDesc = new javax.swing.JTextField();
        ResultNovo = new javax.swing.JButton();
        ProdPreco = new javax.swing.JTextField();
        ProdPag = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        BtnProdAlterar = new javax.swing.JButton();
        ProdQtd = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ResultTable = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        BtnCalc01 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        BtnRelCantina = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        BtnPgnt = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblFechamento = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setState(8);

        Vendas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Vendas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(176, 200, 199));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        VendTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        VendTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(VendTable);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Nome:");

        VendNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendNome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VendNomeKeyReleased(evt);
            }
        });

        VendTable2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        VendTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(VendTable2);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel11.setText("Data da Compra");

        VendData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendData.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendData.setEnabled(false);

        VendTable3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendTable3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VendTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        VendTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VendTable3);
        if (VendTable3.getColumnModel().getColumnCount() > 0) {
            VendTable3.getColumnModel().getColumn(0).setResizable(false);
            VendTable3.getColumnModel().getColumn(1).setResizable(false);
            VendTable3.getColumnModel().getColumn(2).setResizable(false);
            VendTable3.getColumnModel().getColumn(3).setResizable(false);
        }

        LblBemVindo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LblBemVindo.setText("Bem Vindo");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setText("Seja Bem Vindo");

        jPanel4.setBackground(new java.awt.Color(176, 200, 199));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        VendAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Finalizar1.jpg"))); // NOI18N
        VendAdd.setToolTipText("Finalizar Venda");
        VendAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendAddMouseClicked(evt);
            }
        });
        VendAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VendAddActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(255, 255, 255));
        btnDel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Delet.jpg"))); // NOI18N
        btnDel.setToolTipText("Novo");
        btnDel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        ValorFinal.setFont(new java.awt.Font("FreeSans", 3, 70)); // NOI18N
        ValorFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ValorFinal.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Valor Total:");

        btnFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Adicionar.1.png"))); // NOI18N
        btnFinal.setText("Adcionar");
        btnFinal.setToolTipText("Adicionar");
        btnFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Adicionar:");

        VendQtd.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        VendQtd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendQtd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                VendQtdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                VendQtdFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Qtdade:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(185, 185, 185)
                .addComponent(jLabel10)
                .addGap(84, 84, 84))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(VendQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(VendAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(btnFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(100, 100, 100)
                .addComponent(ValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel18))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(VendAdd))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VendQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(176, 200, 199));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        VendValorT.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        VendValorT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendValorT.setEnabled(false);

        VendValorItem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        VendValorItem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendValorItem.setEnabled(false);
        VendValorItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendValorItemMouseClicked(evt);
            }
        });

        VendProduto.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        VendProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VendProdutoKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("SubTotal:");

        jLabel17.setFont(new java.awt.Font("Likhan", 1, 14)); // NOI18N
        jLabel17.setText("Valor por Item:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Produto");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(VendProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel17)
                .addGap(36, 36, 36)
                .addComponent(VendValorItem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(VendValor, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(VendValorT, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VendProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(VendValorItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendValorT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel17)
                    .addComponent(VendValor, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(VendNome, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblBemVindo)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(VendData, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)))))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(LblBemVindo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VendNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendData, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        Vendas.addTab("Vendas", jPanel1);

        jPanel2.setBackground(new java.awt.Color(184, 163, 163));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(960, 700));
        jPanel2.setPreferredSize(new java.awt.Dimension(964, 700));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Pesquisa");

        ResultPesq.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ResultPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultPesqActionPerformed(evt);
            }
        });
        ResultPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ResultPesqKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Id");

        ResultId.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        ResultId.setEnabled(false);

        BtnVendido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtnVendido.setText("Vendidos");
        BtnVendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVendidoActionPerformed(evt);
            }
        });
        BtnVendido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnVendidoKeyReleased(evt);
            }
        });

        ResultEstoque.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ResultEstoque.setText("Estoque");
        ResultEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultEstoqueActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(184, 163, 163));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnAdic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Adicionar.1.png"))); // NOI18N
        BtnAdic.setText("Adicionar Produto");
        BtnAdic.setToolTipText("Adiciona Produto");
        BtnAdic.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAdic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnAdic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdicActionPerformed(evt);
            }
        });

        ProdDEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/cancelar.jpg"))); // NOI18N
        ProdDEL.setText("Apaga Venda");
        ProdDEL.setToolTipText("Apaga Venda");
        ProdDEL.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ProdDEL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProdDEL.setEnabled(false);
        ProdDEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdDELActionPerformed(evt);
            }
        });

        BtnApagaP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/images444.png"))); // NOI18N
        BtnApagaP.setText("Apaga Produto");
        BtnApagaP.setToolTipText("Apga produto");
        BtnApagaP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnApagaP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnApagaP.setEnabled(false);
        BtnApagaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnApagaPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnAdic, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(ProdDEL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(BtnApagaP, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAdic, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnApagaP, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProdDEL, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(184, 163, 163));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Descrição:");

        ProdDesc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        ResultNovo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ResultNovo.setText("Novo");
        ResultNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultNovoActionPerformed(evt);
            }
        });

        ProdPreco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        ProdPag.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Preço por unidade:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Forma de Pagamento");

        BtnProdAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnProdAlterar.setText("Altera Vendido");
        BtnProdAlterar.setToolTipText("Alterar vendidos");
        BtnProdAlterar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnProdAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnProdAlterar.setEnabled(false);
        BtnProdAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProdAlterarActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Qtd/venda");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ProdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(107, 107, 107)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ProdQtd))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ProdPag, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnProdAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(ResultNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResultNovo))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel23))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ProdPag, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ProdQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ProdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnProdAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ResultTable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ResultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResultTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ResultTable);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane7.setViewportView(jTextArea1);

        BtnCalc01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Calculadora.1.png"))); // NOI18N
        BtnCalc01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalc01ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Lembrete");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Calculadora");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel24))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(BtnCalc01, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(ResultId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(BtnVendido)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ResultPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ResultEstoque)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6)
                                    .addGap(91, 91, 91)))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResultEstoque)
                    .addComponent(ResultPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(BtnVendido))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnCalc01)
                                .addGap(57, 57, 57))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResultId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(14, 14, 14)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );

        Vendas.addTab("Resultados", jPanel2);

        jPanel3.setBackground(new java.awt.Color(153, 152, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Vani", 0, 36)); // NOI18N
        jLabel1.setText("Impressão de Relatorios da Cantina");

        jPanel8.setBackground(new java.awt.Color(153, 153, 173));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnRelCantina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Relatório.png"))); // NOI18N
        BtnRelCantina.setText("Relatório");
        BtnRelCantina.setToolTipText("Impressão de Relatório");
        BtnRelCantina.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnRelCantina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRelCantinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnRelCantina, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnRelCantina, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(153, 153, 173));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/down-arrow.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel13)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(395, 395, 395))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(583, 583, 583)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(599, 599, 599)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
        );

        Vendas.addTab("Relatório da Cantina", jPanel3);

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane2.setForeground(new java.awt.Color(51, 0, 51));
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel11.setBackground(new java.awt.Color(153, 165, 150));

        BtnPgnt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnPgnt.setText("Resumo do Dia");
        BtnPgnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPgntActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        tblFechamento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblFechamento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblFechamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tblFechamento);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(614, 614, 614)
                        .addComponent(BtnPgnt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(373, 373, 373)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(374, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(BtnPgnt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Fechameto de caixa", jPanel11);

        jPanel12.setForeground(new java.awt.Color(127, 166, 174));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel28.setText("Sistema de Vendas da Segunda Igreja Batista no Recanto das Emas - SIBRE");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("Este programa foi desenvolvido com a finalidade de suprir uma necessidade basica de vendas na cantina da SIBRE");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("pois era necessario que se soubesse quando hove a venda e a forma de pagamento como esta venda foi feita ");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("afim de apresentar o relatório da mesma para conferência e também para ajustes de contas nas reuniões da SIBRE.");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setText("Toda honra e toda a glória seja dada ao Senhor nosso Jesus Cristo para gloria de Deus Pai.");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("No Senhor faremo proezas: Salmos 108:13");

        jLabel14.setText("Desenvolvido por Ernilson Daniel Lima de Souza");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/SibreLogin.png"))); // NOI18N

        jLabel21.setText("Sistema desenvolvido por: Ernilson Daniel Lima de Souza");
        jLabel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("No Senhor faremos proesas. Salmos 108:13 "); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel30))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel29))))
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(261, 261, 261))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(285, 285, 285))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(jLabel20))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(491, 491, 491)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel33)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addGap(108, 108, 108)
                .addComponent(jLabel21)
                .addGap(121, 121, 121)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Sobre", jPanel12);

        Vendas.addTab("Ferramentas", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Vendas, javax.swing.GroupLayout.PREFERRED_SIZE, 1358, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Vendas, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPgntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPgntActionPerformed

        Tentativa_A("select  sum(qtdp) as Quantidade, sum(qtdp * valor_item) as Valor_Total, forma_pg as Forma_de_Pagamento from venda where forma_pg like '%Fiado%'"
                + "union select  sum(qtdp) as Quantidade, sum(qtdp * valor_item) as Valor_Total, forma_pg as Forma_de_Pagamento from venda where forma_pg like '%Dinheiro%'"
                + "union select  sum(qtdp) as Quantidade, sum(qtdp * valor_item) as Valor_Total, forma_pg as Forma_de_Pagamento from venda where forma_pg like '%Débito%'"
                + "union select  sum(qtdp) as Quantidade, sum(qtdp * valor_item) as Valor_Total, forma_pg as Forma_de_Pagamento from venda where forma_pg like '%Credito%'");
    }//GEN-LAST:event_BtnPgntActionPerformed

    private void BtnRelCantinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRelCantinaActionPerformed
        // Essa função é para gerar relatório
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desse deste relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            conexao.conector();
            //imprimindo o relatório com o framework JasperReports
            try {
                conexao.executaSQL("select * from venda order by dataq desc;");
                JRResultSetDataSource relatResult = new JRResultSetDataSource(conexao.rs);
                JasperPrint print = JasperFillManager.fillReport("D:\\boots\\ProjetoVenda-5.1-MySQL\\Report\\MyReports\\Gal.jasper", new HashMap(), relatResult);
                JasperViewer jv = new JasperViewer(print, false); //Cria instancia para impressão
                jv.setVisible(true); //chama o relatório para visualização\\C:\Users\eupen\JaspersoftWorkspace\MyReports\Cantina3.jrxml
                jv.toFront(); // set o formuloari a frente da aplicação// src/Cantina1.jasper
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_BtnRelCantinaActionPerformed

    private void BtnProdAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProdAlterarActionPerformed
        PesquisaPegaPedido();
        LimparEstoque();
    }//GEN-LAST:event_BtnProdAlterarActionPerformed

    private void ResultNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultNovoActionPerformed
        ProdDesc.setEnabled(true);
        ProdPreco.setEnabled(true);
        ProdQtd.setEnabled(true);
        ResultEstoque.setEnabled(true);
        ProdDEL.setEnabled(false);
        BtnAdic.setEnabled(true);
        BtnVendido.setEnabled(true);
        BtnApagaP.setEnabled(false);
        LimparEstoque();
    }//GEN-LAST:event_ResultNovoActionPerformed

    private void BtnApagaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnApagaPActionPerformed
        int id_pro = Integer.parseInt(ResultId.getText());
        String descrica = this.ProdDesc.getText();
        String qtd = this.ProdQtd.getText();
        String preco = this.ProdPreco.getText(); // preço por unidade da aba Result "valor Item".

        bl2.ExcluirProduto(id_pro, descrica, preco, preco);
        ResultTable.setVisible(false);
    }//GEN-LAST:event_BtnApagaPActionPerformed

    private void ProdDELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdDELActionPerformed
        int id = Integer.parseInt(ResultId.getText());
        String nome = this.VendNome.getText();
        String descrica = this.VendProduto.getText();
        String qtd = this.VendQtd.getText();
        String total = this.VendValorT.getText();
        //String cboCont = this.Contopassado.getSelectedItem().toString();
        bll.ExcluirVenda(id, nome, descrica, qtd, total);
        LimparEstoque();
    }//GEN-LAST:event_ProdDELActionPerformed

    private void BtnAdicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdicActionPerformed
        String descricao = this.ProdDesc.getText();
        String qtd = this.ProdQtd.getText();
        String preco = this.ProdPreco.getText();
        bl2.salvaProduto(descricao, qtd, preco);
        LimparEstoque();
    }//GEN-LAST:event_BtnAdicActionPerformed

    private void ResultEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultEstoqueActionPerformed
        pesquisaVenda("select id_pro as Id, descricao_p as Descrição, qtd as Quantidade, preco as Preço, dataq as Data from produtos where descricao_p like '%" + ResultPesq.getText() + "%'order by dataq desc");
        ProdDesc.setEnabled(false);
        ProdPreco.setEnabled(false);
        ProdQtd.setEnabled(false);
        ProdDEL.setEnabled(false);
        BtnApagaP.setEnabled(true);
        ResultTable.setVisible(true);
        ProdPag.setEnabled(false);
        BtnProdAlterar.setEnabled(false);
        BtnAdic.setEnabled(false);
        flag = 2;
    }//GEN-LAST:event_ResultEstoqueActionPerformed

    private void BtnVendidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnVendidoKeyReleased

    }//GEN-LAST:event_BtnVendidoKeyReleased

    private void BtnVendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVendidoActionPerformed
        pesquisaProduto("select id_v as Id, nome as Nome, descricao as Descrição, valor_item as Valor_Item, qtdp as Quantidade,valor_sub_total as Sub_Total,forma_pg as Forma_Pg, dataq as Data from venda where nome like '%" + ResultPesq.getText() + "%'order by dataq desc");
        ProdDEL.setEnabled(true);
        BtnAdic.setEnabled(false);
        BtnApagaP.setEnabled(false);
        ProdDesc.setEnabled(false);
        ProdQtd.setEnabled(false);
        ProdPreco.setEnabled(false);
        ResultTable.setVisible(true);
        ProdPag.setEnabled(true);
        BtnProdAlterar.setEnabled(true);
        BtnAdic.setEnabled(false);
        flag = 1;
    }//GEN-LAST:event_BtnVendidoActionPerformed

    private void ResultPesqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResultPesqKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ResultPesqKeyReleased

    private void ResultPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultPesqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResultPesqActionPerformed

    private void ResultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResultTableMouseClicked
        if (flag == 2) {
            setarReult();
        } else {
            setarVenda();
        }
        ProdDesc.setEnabled(true);
        ProdQtd.setEnabled(true);
        ProdPreco.setEnabled(true);
    }//GEN-LAST:event_ResultTableMouseClicked

    private void VendProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VendProdutoKeyReleased
        pesquisaProdutos();
        VendNome.setEnabled(false);
        VendTable2.setVisible(true);
        VendQtd.setEnabled(true);
        btnFinal.setEnabled(true);
    }//GEN-LAST:event_VendProdutoKeyReleased

    private void VendValorItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendValorItemMouseClicked

    }//GEN-LAST:event_VendValorItemMouseClicked

    private void VendQtdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_VendQtdFocusLost
        float valorTotal;
        valorTotal = Float.parseFloat(VendValorItem.getText()) * Integer.parseInt(VendQtd.getText());
        VendValorT.setText(String.valueOf(valorTotal));        
    }//GEN-LAST:event_VendQtdFocusLost

    private void VendQtdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_VendQtdFocusGained
        float valorTotal;
        valorTotal = Float.parseFloat(VendValorItem.getText()) * Integer.parseInt(VendQtd.getText());
        VendValorT.setText(String.valueOf(valorTotal));
       
    }//GEN-LAST:event_VendQtdFocusGained

//int total = Integer.parseInt(VendValorT.getText());
    private void btnFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalActionPerformed
        Integer adicionado;
        adicionado = Integer.parseInt(VendQtd.getText());

        if (adicionado == 0) {
            JOptionPane.showMessageDialog(null, "Confirme a quantidade");
        } else {
            Leandro();
            String nome = this.VendNome.getText();
            String descrica = this.VendProduto.getText();
            String qtd = VendQtd.getText();
            String total = this.VendValorT.getText();
            String vfinal = this.ValorFinal.getText();
            String vItem = this.VendValor.getText();
            bll.Salvar_vendaCarrinho(nome, descrica, qtd, total, vfinal,vItem);
             
            VisualiaCarrinho("select nome as Nome, descricao as Descrição, valor_item as Valor_Item,qtdp as Qtd, valor_sub_total as Sub_Total, valor_total as Valor_Total from carrinho; ");
            VendQtd.setEnabled(false);
            VendAdd.setEnabled(true);
            btnFinal.setEnabled(false);
            limpaTela();

        }

    }//GEN-LAST:event_btnFinalActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        limpaTela2();
        excluir();
        VendNome.setEnabled(true);
        VendTable3.setVisible(false);
        ValorFinal.setText("0");

    }//GEN-LAST:event_btnDelActionPerformed

    private void VendAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VendAddActionPerformed
        VendNome.setText("");
        // CboCont.setToolTipText("");
        // VendTable3.setVisible(false);
        ValorFinal.setText("0");
        VendValorT.setText("0");
        VendProduto.setText("");
        VendQtd.setEnabled(false);
        VendProduto.setEnabled(false);
        btnFinal.setEnabled(false);
        TPagamento tp = new TPagamento();
        tp.setVisible(true);
        //   Carrinho();
//        excluir();
    }//GEN-LAST:event_VendAddActionPerformed

    private void VendAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VendAddMouseClicked

    private void VendTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VendTable3MouseClicked

    private void VendTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTable2MouseClicked
        setarProduto();
        VendQtd.setEnabled(true);
        VendQtd.setText("0");
        VendTable2.setVisible(false);
        VendTable3.setVisible(true);
        VisualiaCarrinho("select nome as Nome, descricao as Descrição, valor_item as Valor_Item, valor_sub_total as Sub_Total, qtdp as Qtd, forma_pg as Forma_pg from carrinho; ");
    }//GEN-LAST:event_VendTable2MouseClicked

    private void VendNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VendNomeKeyReleased
        pesquisaCadastro();
        VendTable.setVisible(true);
        VendProduto.setEnabled(true);
        btnFinal.setEnabled(true);
    }//GEN-LAST:event_VendNomeKeyReleased

    private void VendTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTableMouseClicked
        setarCadastro();
        VendTable.setVisible(false);
    }//GEN-LAST:event_VendTableMouseClicked

    private void BtnCalc01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalc01ActionPerformed
         actionPerformed();
    }//GEN-LAST:event_BtnCalc01ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdic;
    public static javax.swing.JButton BtnApagaP;
    private javax.swing.JButton BtnCalc01;
    private javax.swing.JButton BtnPgnt;
    public static javax.swing.JButton BtnProdAlterar;
    private javax.swing.JButton BtnRelCantina;
    private javax.swing.JButton BtnVendido;
    public static javax.swing.JLabel LblBemVindo;
    public static javax.swing.JButton ProdDEL;
    private javax.swing.JTextField ProdDesc;
    private javax.swing.JTextField ProdPag;
    private javax.swing.JTextField ProdPreco;
    private javax.swing.JTextField ProdQtd;
    private javax.swing.JButton ResultEstoque;
    private javax.swing.JTextField ResultId;
    private javax.swing.JButton ResultNovo;
    private javax.swing.JTextField ResultPesq;
    private javax.swing.JTable ResultTable;
    public static javax.swing.JTextField ValorFinal;
    public static javax.swing.JButton VendAdd;
    private javax.swing.JTextField VendData;
    public static javax.swing.JTextField VendNome;
    public static javax.swing.JTextField VendProduto;
    public static javax.swing.JTextField VendQtd;
    private javax.swing.JTable VendTable;
    private javax.swing.JTable VendTable2;
    public static javax.swing.JTable VendTable3;
    private javax.swing.JTextField VendValor;
    public static javax.swing.JTextField VendValorItem;
    private javax.swing.JTextField VendValorT;
    private javax.swing.JTabbedPane Vendas;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnFinal;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tblFechamento;
    // End of variables declaration//GEN-END:variables

    private void setDefaultCloseOpration(int EXIT_ON_CLOSE) {
        setLocationRelativeTo(null);

    }
}

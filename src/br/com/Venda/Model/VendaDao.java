/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Venda.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import br.com.Venda.Model.vendaDTO;
import br.com.Venda.DAL.ModuloConexao;

public class VendaDao {

    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void salvarVendaCarrinho(vendaDTO dto) {
        ModuloConexao conexao = new ModuloConexao();
        String sql = "insert into carrinho (nome, descricao, qtdp, valor_item, valor_sub_total, valor_total )value (?,?,?,?,?,?)";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getNome());
            pst.setString(2, dto.getDescricao());
            pst.setString(3, dto.getQtd());
            pst.setString(4, dto.getTotal());
            pst.setString(5, dto.getValorItem());
            pst.setString(6, dto.getVsubtotal());

//            if ((dto.getNome().isEmpty()) || (dto.getDescricao().isEmpty()||(dto.getTotal().isEmpty()))) {
//                JOptionPane.showMessageDialog(null, "Esqueceu de preencher o campo 'Nome' ou 'Produto' ou valor Total!");
//            } else {
            int adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                // JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
//                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ExcluirVenda(vendaDTO dto) {
        conexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from venda where id_v = ?";
            try {
                pst = conexao.conn.prepareStatement(sql);
                pst.setInt(1, dto.getId());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erro");

            }
        }
    }

    public void salvarCarrinho(vendaDTO dto) {
        //"insert into carrinho (nome, descricao, qtdp,valor, forma_pg) select nome, descricao, qtdp,valor, forma_pg from venda; ";
        ModuloConexao conexao = new ModuloConexao();
        String sql = "insert into teste (forma_pg )value (?)";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getCboCont());

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                // JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

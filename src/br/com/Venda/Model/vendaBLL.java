/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Venda.Model;

import br.com.Venda.DAL.ModuloConexao;
import br.com.Venda.Model.vendaDTO;
import br.com.Venda.Model.VendaDao;

/**
 *
 * @author T69779848134
 */
public class vendaBLL {

    VendaDao dao = new VendaDao();
    vendaDTO dto = new vendaDTO();

    public void Salvar_vendaCarrinho(String nome, String descricao, String qtd, String total, String vfinal, String vItem) {
        dto.setNome(nome);
        dto.setDescricao(descricao);
        dto.setQtd(qtd);
        dto.setTotal(vItem);
        dto.setValorItem(total);
        dto.setVsubtotal(vfinal);

        dao.salvarVendaCarrinho(dto);
    }

    public void ExcluirVenda(int id, String nome, String descricao, String qtd, String total) {
        dto.setId(id);
        dto.setNome(nome);
        dto.setDescricao(descricao);
        dto.setQtd(qtd);
        dto.setTotal(total);
        // dto.setCboCont(CboCont);

        dao.ExcluirVenda(dto);
    }

    public void SalvaCarrinho(String cob) {
        int x = 1;
        while (x < 10) {

            dto.setCboCont(cob + x++);

            dao.salvarCarrinho(dto);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Utils;

import br.com.Produto.Model.Carrinho;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author T69779848134
 */
public class TableCarrinho extends AbstractTableModel {

    private static final int colId = 0;
    private static final int colNome = 1;
    private static final int colDescricao = 2;
    private static final int colQtd = 3;
    private static final int colValor = 4;
    private static final int colForm_pg = 5;
    private static final int colData = 6;

    private ArrayList<Carrinho> linhas;

    private final String[] colunas = new String[] {"Nome", "Descriçao", "Quantd", "Valor", "Forma_pg", "Dataq"};

    public TableCarrinho() {
        linhas = new ArrayList<>();
    }

//     public TableCarrinho(List<Carrinho> listCar){
//         linhas = new ArrayList<>(listCar);
//     }
     
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case colId:
                return Integer.class;
            case colNome:
                return String.class;
            case colDescricao:
                return String.class;
            case colQtd:
                return String.class;
            case colValor:
                return String.class;
            case colForm_pg:
                return String.class;
            case colData:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("Valor retornado invalido.");
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Carrinho c = linhas.get(rowIndex);
        switch (columnIndex) {
            case colId:
                return c.getId_v();
            case colNome:
                return c.getNome();
            case colDescricao:
                return c.getDescricao();
            case colQtd:
                return c.getQtd();
            case colValor:
                return c.getValor();
            case colForm_pg:
                return c.getForma_pg();
            case colData:
                return c.getDataq();
            default:
                throw new IndexOutOfBoundsException("Coluna invalida");
        }
    }

    public Carrinho get(int row) {
        return linhas.get(row);
    }
}

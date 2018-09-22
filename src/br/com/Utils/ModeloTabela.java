/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Utils;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ernilson
 */
public class ModeloTabela extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;

    public ModeloTabela(ArrayList linha, String[] col) {
        setLinhas(linha);
        setColunas(col);

    }

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList dados) {
        linhas = dados;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] nomes) {
        colunas = nomes;
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public int getRowCount() {
        return linhas.size();
    }

    public String getColumnName(int numCol) {
        return colunas[numCol];
    }

    public Object getValueAt(int numLinha, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLinha);
        return linha[numCol];
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Produto.Model;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;

/**
 *
 * @author T69779848134
 */
public class Carrinho {
    private int id_v;
    private String nome;
    private String descricao;
    private String qtd;
    private String valor;
    private String forma_pg;
    private Date dataq;

    public Carrinho() {
    }

    public Carrinho(int id_v, String nome, String descricao, String qtd, String valor, String forma_pg, Date dataq) {
        this.id_v = id_v;
        this.nome = nome;
        this.descricao = descricao;
        this.qtd = qtd;
        this.valor = valor;
        this.forma_pg = forma_pg;
        this.dataq = dataq;
    }

    public int getId_v() {
        return id_v;
    }

    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getForma_pg() {
        return forma_pg;
    }

    public void setForma_pg(String forma_pg) {
        this.forma_pg = forma_pg;
    }

    public Date getDataq() {
        return dataq;
    }

    public void setDataq(Date dataq) {
        this.dataq = dataq;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_v;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carrinho other = (Carrinho) obj;
        if (this.id_v != other.id_v) {
            return false;
        }
        return true;
    }
    
    
}

package br.com.codenation.time;

import br.com.codenation.jogador.Jogador;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;


public class Time {

    private Long id;

    private  String nome;

    private LocalDate dataCriacao;

    private String corUniformePrincipal;

    private String corUniformeSecundario;

    private List<Jogador> jogadores;


    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

        this.id = id;

        this.nome = nome;

        this.dataCriacao = dataCriacao;

        this.corUniformePrincipal = corUniformePrincipal;

        this.corUniformeSecundario = corUniformeSecundario;

        this.jogadores = new ArrayList<>();

    }


    public List<Jogador> getJogadores() {

        return jogadores;

    }


    public Long getId() {

        return id;

    }


    public void setId(Long id) {

        this.id = id;

    }


    public String getNome() {

        return nome;

    }


    public void setNome(String nome) {

        this.nome = nome;

    }


    public LocalDate getDataCriacao() {

        return dataCriacao;

    }


    public void setDataCriacao(LocalDate dataCriacao) {

        this.dataCriacao = dataCriacao;

    }


    public String getCorUniformePrincipal() {

        return corUniformePrincipal;

    }


    public void setCorUniformePrincipal(String corUniformePrincipal) {

        this.corUniformePrincipal = corUniformePrincipal;

    }


    public String getCorUniformeSecundario() {

        return corUniformeSecundario;

    }


    public void setCorUniformeSecundario(String corUniformeSecundario) {

        this.corUniformeSecundario = corUniformeSecundario;

    }


    public void incluirJogador(Jogador jogador) {

        this.jogadores.add(jogador);

    }

}

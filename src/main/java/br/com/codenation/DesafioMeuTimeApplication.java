package br.com.codenation;


import br.com.codenation.exceptions.CapitaoNaoInformadoException;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;

import br.com.codenation.exceptions.JogadorNaoEncontradoException;

import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.Jogador;
import br.com.codenation.time.Time;


import java.math.BigDecimal;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;



public class DesafioMeuTimeApplication implements MeuTimeInterface {


	private final List<Time> times;


	public DesafioMeuTimeApplication() {

		this.times = new ArrayList<Time>();

	}


	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		if (times.stream().anyMatch(t -> t.getId().equals(id)))

			throw new IdentificadorUtilizadoException();

		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));

	}


	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		for (Time time : times) {

			if(time.getJogadores().stream().anyMatch(jogador -> jogador.getId().equals(id)))

				throw new IdentificadorUtilizadoException();

		}

		Time time = times.stream().filter(time1 -> time1.getId() == idTime).findFirst().orElseThrow(TimeNaoEncontradoException::new);

		time.getJogadores().add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade,salario));


	}


	public void definirCapitao(Long idJogador) {

		boolean encontrouCapitao = false;

		for (Time time : times) {

			for (Jogador jogador: time.getJogadores()) {

				if (jogador.getId() == idJogador) {

					jogador.setCapitao(true);

					encontrouCapitao = true;

					break;

				}

			}

			if (encontrouCapitao) {

				for (Jogador jogador : time.getJogadores()) {

					if (!jogador.getId().equals(idJogador))

						jogador.setCapitao(false);

				}

				break;

			}

		}

		if (!encontrouCapitao) {

			throw new JogadorNaoEncontradoException();

		}

	}


	public Long buscarCapitaoDoTime(Long idTime) {

		Time time = times.stream().filter(time1 -> time1.getId() == idTime).findFirst().orElseThrow(TimeNaoEncontradoException::new);

		for (Jogador jogador: time.getJogadores()) {

			if (jogador.isCapitao()){

				return jogador.getId();

			}

		}

		throw new CapitaoNaoInformadoException();

	}


	public String buscarNomeJogador(Long idJogador) {

		for (Time time : times)
			for (Jogador jogador : time.getJogadores()) {

				if (jogador.getId().equals(idJogador)) {

					return jogador.getNome();

				}

			}

		throw new JogadorNaoEncontradoException();

	}


	public String buscarNomeTime(Long idTime) {

		Time time = times.stream().filter(time1 -> time1.getId().equals(idTime)).findFirst().orElseThrow(TimeNaoEncontradoException::new);

		return time.getNome();

	}


	public List<Long> buscarJogadoresDoTime(Long idTime) {

		Time time = times.stream().filter(time1 -> time1.getId().equals(idTime)).findFirst().orElseThrow(TimeNaoEncontradoException::new);

		return time.getJogadores().stream().map(Jogador::getId).collect(Collectors.toList());

	}


	public Long buscarMelhorJogadorDoTime(Long idTime) {

		Time time = times.stream().filter(time1 -> time1.getId().equals(idTime)).findFirst().orElseThrow(TimeNaoEncontradoException::new);

		Jogador melhorJogador = null;

		for (Jogador jogador: time.getJogadores()) {

			if (melhorJogador == null || jogador.getNivelHabilidade() > melhorJogador.getNivelHabilidade()) {

				melhorJogador = jogador;

			}

		}

		assert melhorJogador != null;
		return melhorJogador.getId();

	}


	public Long buscarJogadorMaisVelho(Long idTime) {

		Time time = times.stream().filter(time1 -> Objects.equals(time1.getId(), idTime)).findFirst().orElseThrow(TimeNaoEncontradoException::new);

		Jogador jogadorMaisVelho = null;

		for (Jogador jogador: time.getJogadores()) {

			if (jogadorMaisVelho == null || jogador.getDataNascimento().isBefore(jogadorMaisVelho.getDataNascimento()))  {

				jogadorMaisVelho = jogador;

			}

		}

		assert jogadorMaisVelho != null;
		return jogadorMaisVelho.getId();

	}


	public List<Long> buscarTimes() {

		return this.times.stream().map(Time::getId).collect(Collectors.toList());

	}


	public Long buscarJogadorMaiorSalario(Long idTime) {

		Time time = times.stream().filter(time1 -> time1.getId().equals(idTime)).findFirst().orElseThrow(TimeNaoEncontradoException::new);

		Jogador jogadorComMaiorSalario = null;

		for (Jogador jogador: time.getJogadores()) {

			if (jogadorComMaiorSalario == null || jogador.getSalario().compareTo(jogadorComMaiorSalario.getSalario()) > 0) {

				jogadorComMaiorSalario = jogador;

			}

		}

		assert jogadorComMaiorSalario != null;
		return jogadorComMaiorSalario.getId();

	}


	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		for (Time time : times) {

			for (Jogador jogador : time.getJogadores()) {

				if (jogador.getId().equals(idJogador)) {

					return jogador.getSalario();

				}

			}

		}

		throw new JogadorNaoEncontradoException();

	}


	public List<Long> buscarTopJogadores(Integer top) {

		List<Jogador> todosJogadores = new ArrayList<>();

		for (Time time: times) {

			todosJogadores.addAll(time.getJogadores());

		}

		todosJogadores.sort(Collections.reverseOrder());

		return todosJogadores.stream().map(Jogador::getId).limit(top).collect(Collectors.toList());

	}


}


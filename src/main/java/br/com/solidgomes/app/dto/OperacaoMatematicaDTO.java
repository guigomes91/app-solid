package br.com.solidgomes.app.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoMatematicaDTO {

	private String tipoOperacao;
	private BigDecimal resultado;
}

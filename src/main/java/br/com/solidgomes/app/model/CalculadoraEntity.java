package br.com.solidgomes.app.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "calculadora")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Nonnull
	@Column(name = "tipo_calculo")
	private String tipoCalculo;
	
	@Nonnull
	@Column(name = "valor_x")
	private BigDecimal x;
	
	@Nonnull
	@Column(name = "valor_y")
	private BigDecimal y;
	
	@Nonnull
	@Column(name = "resultado")
	private BigDecimal resultado;
}

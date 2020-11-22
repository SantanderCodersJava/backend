package br.com.dh.Banco.de.Sangue.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.dh.Banco.de.Sangue.enums.Sangue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doador")
    private Integer id;
    
    private String nome;
    private String rg;
    
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    
    private String email;
    private String cpf;
    private String telefone;
    private String sexo;
    
    @Column(name = "tipo_sanguineo")
    private Sangue tipoSanguineo;
    
    private String senha;
    
    @Column(name = "caminho_img")
    private String caminhoImg;       

	@OneToMany(orphanRemoval = true, mappedBy = "doador")
    @JsonIgnoreProperties("doador")
    private List<Endereco> enderecos; 
    
    @OneToMany(orphanRemoval = true, mappedBy = "doador")
    @JsonIgnoreProperties("doador")
    private List<Agendamento> agendamentos;
    
}

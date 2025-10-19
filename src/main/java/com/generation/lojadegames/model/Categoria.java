package com.generation.lojadegames.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importa a anotação para ignorar propriedades durante a serialização JSON
import java.util.List; // Importa a classe List para definir listas de objetos

import jakarta.persistence.CascadeType; // Importa a anotação para definir o comportamento de cascata
import jakarta.persistence.Column; // Importa a anotação para definir propriedades da coluna no banco de dados
import jakarta.persistence.Entity; // Importa a anotação para definir uma entidade JPA
import jakarta.persistence.FetchType; // Importa a enumeração para definir o tipo de busca
import jakarta.persistence.GeneratedValue; // Importa a anotação para definir a estratégia de geração de valores
import jakarta.persistence.GenerationType; // Importa a enumeração para estratégias de geração de valores
import jakarta.persistence.Id; // Importa a anotação para definir a chave primária
import jakarta.persistence.OneToMany; // Importa a anotação para definir o relacionamento um-para-muitos
import jakarta.persistence.Table; //  Importa a anotação para definir o nome da tabela no banco de dados
import jakarta.validation.constraints.NotBlank; // Importa a anotação para validar que o campo não seja nulo ou vazio
import jakarta.validation.constraints.Size; // Importa a anotação para validar o tamanho dos campos

@Entity // inidica ao spring que essa classe é uma entidade (tabela)
@Table(name = "tb_categorias") // Define o nome da tabela que sera criada no banco de dados
public class Categoria { //indica que a classe é uma entidade do JPA

    // Variáveis de instância (atributos) e validações dos dados / Atributos e métodos serão implementados aqui
	@Id  // PRIMARY KEY(id) // indica que o atributo id é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT, quem cuida disso é o DB
	private Long id; // Define o atributo id

    @Column(length = 1000) // Define o tamanho máximo do campo no banco de dados
	@NotBlank(message = "O atributo Nome é obrigatório!") // Impedir que a descricao seja em branco
	@Size(min = 3, max = 50, message = "O atributo Nome deve conter no mínimo 3 e no máximo 50 caracteres") // Define o tamanho mínimo e máximo do campo
	private String nome; // Define o atributo descricao 

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE) // Define o relacionamento um-para-muitos com Postagem ( uma categoria para muitos produtos)   
	@JsonIgnoreProperties(value = "categoria", allowSetters = true) // Ignora a propriedade postagem para evitar recursão infinita durante a serialização JSON
	private List<Produto> produto; // Define o atributo postagem como uma lista de Postagem

    // Getters e Setters dos atributos
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

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
    
}

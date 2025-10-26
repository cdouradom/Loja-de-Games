package com.generation.lojadegames.model; //indica que a classe pertence ao pacote model dentro do projeto lojadegames

import java.time.LocalDateTime; // Importa a classe LocalDateTime para manipulação de data e hora

import org.hibernate.annotations.UpdateTimestamp; // Importa a anotação para atualizar automaticamente o timestamp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importa a anotação para ignorar propriedades durante a serialização JSON

import jakarta.persistence.Column; // Importa a anotação para definir propriedades da coluna no banco de dados
import jakarta.persistence.Entity; // Importa a anotação para definir uma entidade JPA
import jakarta.persistence.Table; //  Importa a anotação para definir o nome da tabela no banco de dados
import jakarta.validation.constraints.Size; // Importa a anotação para validar o tamanho dos campos
import jakarta.validation.constraints.NotBlank; // Importa a anotação para validar que o campo não seja nulo ou vazio
import jakarta.validation.constraints.NotNull; // Importa a anotação para validar que o campo não seja nulo
import jakarta.persistence.Id; // Importa a anotação para definir a chave primária
import jakarta.persistence.ManyToOne; // Importa a anotação para definir o relacionamento muitos-para-um
import jakarta.persistence.GeneratedValue; // Importa a anotação para definir a estratégia de geração de valores
import jakarta.persistence.GenerationType; // Importa a enumeração para estratégias de geração de valores

@Entity // inidca ao spring que essa classe é uma entidade (cria a tabela)
@Table(name = "tb_produtos") // Define o nome da tabela que sera criada no banco de dados
public class Produto { //indica que a classe é uma entidade do JPA

    @Id  // PRIMARY KEY(id) // indica que o atributo id é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT, quem cuida disso é o DB, gerando ID incrementais para cada novo objeto
	private Long id;  // Define o atributo id do produto

    //Variáveis de instância (atributos) e validações dos dados / Atributos e métodos serão implementados aqui
	@Column(length = 50) // Define o tamanho máximo do campo no banco de dados
	@NotBlank(message = "O atributo Título é obrigatório!") // Impedir que o título seja em branco
	@Size(min = 5, max = 50, message = "O atributo Título deve conter no mínimo 5 e no máximo 50 caracteres") // Define o tamanho mínimo e máximo do campo
	private String titulo; // Define o atributo titulo 
    
    @Column(length = 2000) // Define o tamanho máximo do campo no banco de dados
    @NotBlank(message = "O atributo descricao é obrigatório!") // Impedir que o Texto seja em branco
    @Size(min = 10, max = 2000, message = "O atributo Texto deve conter no mínimo 10 e no máximo 2000 caracteres") // Define o tamanho mínimo e máximo do campo 
    private String descricao; // Define o atributo descricao do produto

    @UpdateTimestamp // Atualiza a data e hora automaticamente sempre que houver uma atualização no produto
	private LocalDateTime data;  // Define o atributo data como LocalDateTime // Guarda tanto a data quanto a hora

    @NotNull(message = "O atributo preço é obrigatório.") // Valida que o campo não seja nulo
    private float preco; // Define o atributo preço do produto

    @NotNull(message = "O atributo quantidade é obrigatório.") // Valida que o campo não seja nulo
    private int quantidade; // Define o atributo quantidade do produto

    @NotBlank(message = "O atributo foto é obrigatório.") // Impedir que o campo foto seja em branco
    private String foto; // Define o atributo foto do produto

    @ManyToOne // Define o relacionamento um-para-muitos com Categoria ( muitos produtos para uma categoria)
	@JsonIgnoreProperties("produtos") // Ignora a propriedade produtos para evitar recursão infinita durante a serialização JSON
	private Categoria categoria; // Define o atributo postagem como uma lista de Postagem


    @ManyToOne // Define o relacionamento um-para-muitos com Usuario ( muitos produtos para um usuario/vendedor)
	@JsonIgnoreProperties("produtos") // Ignora a propriedade produtos para evitar recursão infinita durante a serialização JSON
	private Usuario usuario; // Define o atributo postagem como uma lista de Postagem

    //Getters e Setters
    public Long getId() {
        return id; // Retorna o valor do atributo id
    }

    public void setId(Long id) {
        this.id = id; // Define o valor do atributo id
    }

    public String getTitulo() {
        return titulo; // Retorna o valor do atributo titulo
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo; // Define o valor do atributo titulo
    }

    public String getDescricao() {
        return descricao; // Retorna o valor do atributo descricao
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao; // Define o valor do atributo descricao
    }

    public LocalDateTime getData() {
        return data;    // Retorna o valor do atributo data
    }

    public void setData(LocalDateTime data) {
        this.data = data;   // Define o valor do atributo data
    }

    public float getPreco() {
        return preco;       // Retorna o valor do atributo preco
    }

    public void setPreco(float preco) {
        this.preco = preco;     // Define o valor do atributo preco
    }

    public int getQuantidade() {
        return quantidade;  // Retorna o valor do atributo quantidade
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;   // Define o valor do atributo quantidade
    }

    public String getFoto() {
        return foto;    // Retorna o valor do atributo foto
    }

    public void setFoto(String foto) {
        this.foto = foto;   // Define o valor do atributo foto
    }

    public Categoria getCategoria() {
        return categoria;   // Retorna o valor do atributo categoria
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;  // Define o valor do atributo categoria
    }

    public Usuario getUsuario() {
        return usuario; // Retorna o valor do atributo usuario
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario; // Define o valor do atributo categoria
    }   

}
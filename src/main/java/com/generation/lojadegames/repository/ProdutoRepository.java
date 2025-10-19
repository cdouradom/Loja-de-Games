package com.generation.lojadegames.repository; // Define o pacote repository onde a interface CategoriaRepository está localizada

import java.util.List; // Importa a classe List para definir listas de objetos

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository do Spring Data JPA
import org.springframework.data.repository.query.Param; // Importa a anotação Param para mapear parâmetros

import com.generation.lojadegames.model.Produto; // Importa a classe Produto do pacote model

// Define a interface ProdutoRepository que estende JpaRepository para a entidade Produto com chave primária do tipo Long
public interface ProdutoRepository extends JpaRepository<Produto, Long> { // Extende JpaRepository para fornecer operações CRUD para a entidade Produto

    // Declara um método para buscar produtos pelo título, ignorando maiúsculas e minúsculas através de uma consulta derivada que o Spring Data JPA implementa automaticamente
    public List<Produto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    // Produtos com preço maior que xxx, em ordem crescente
    List<Produto> findByPrecoGreaterThanOrderByPrecoAsc(Float preco);

    // Produtos com preço maior ou igual a 400 (ordem crescente)
    List<Produto> findByPrecoGreaterThanEqualOrderByPrecoAsc(Float preco);

    // Produtos com preço menor que xxx, em ordem decrescente
    List<Produto> findByPrecoLessThanOrderByPrecoDesc(Float preco);

    // Produtos com preço menor ou igual a 400 (ordem decrescente)
    List<Produto> findByPrecoLessThanEqualOrderByPrecoDesc(Float preco);

}

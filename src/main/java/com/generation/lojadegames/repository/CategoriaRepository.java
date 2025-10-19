package com.generation.lojadegames.repository; // Define o pacote repository onde a interface CategoriaRepository está localizada

import java.util.List; // Importa a classe List para definir listas de objetos

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository do Spring Data JPA
import org.springframework.data.repository.query.Param; // Importa a anotação Param para mapear parâmetros

import com.generation.lojadegames.model.Categoria;

// Define a interface CategoriaRepository que estende JpaRepository para a entidade Categoria com chave primária do tipo Long
public interface CategoriaRepository extends JpaRepository<Categoria, Long> { // Extende JpaRepository para fornecer operações CRUD para a entidade Categoria

    // Declara um método para buscar categorias pelo nome, ignorando maiúsculas e minúsculas através de uma consulta derivada que o Spring Data JPA implementa automaticamente
    public List<Categoria> findAllByNomeContainingIgnoreCase(@Param("nome") String nome); // Declara um método para buscar categorias pelo nome, ignorando maiúsculas e minúsculas
}
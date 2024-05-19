package br.com.fiapmsprodutos.infrastructure.database.repository;

import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
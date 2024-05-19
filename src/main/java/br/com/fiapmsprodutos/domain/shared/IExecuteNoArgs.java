package br.com.fiapmsprodutos.domain.shared;

import java.util.List;

public interface IExecuteNoArgs<T> {
    List<T> execute();
}

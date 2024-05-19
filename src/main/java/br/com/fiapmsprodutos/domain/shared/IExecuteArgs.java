package br.com.fiapmsprodutos.domain.shared;

public interface IExecuteArgs<T, J> {
    T execute(J domainObject);
}

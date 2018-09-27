package com.maiorovi.expenses.mapper;

public interface Mapper<S, T> {

    S toDataTransferObject(T t);

    T toDomainObject(S s);

}

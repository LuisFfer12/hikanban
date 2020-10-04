package br.com.hikanban.utils.mapper;

import java.util.List;

public interface DTOMapper<T, V> {

	T mapToDTO(V from);
	
	V mapToORM(T from);
	
	List<T> mapToDTOList(List<V> from);
	
	List<V> mapToORMList(List<T> from);
	
	V mapInfoFromDTO(T dto, V orm);
}

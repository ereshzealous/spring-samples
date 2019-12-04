package com.eresh.graphql.dto;

import com.eresh.graphql.enums.SortOrder;
import io.leangen.graphql.annotations.GraphQLInputField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created By Gorantla, Eresh on 04/Dec/2019
 **/
@Getter
@Setter
@NoArgsConstructor
public class PageInfo {

	@GraphQLInputField(defaultValue = "0")
	private int page;

	@GraphQLInputField(defaultValue = "10")
	private int size;

	//The default sort fields are specified in the service classes
	private String sortField;

	@GraphQLInputField(defaultValue = "\"ASC\"")
	private SortOrder sortOrder;
}

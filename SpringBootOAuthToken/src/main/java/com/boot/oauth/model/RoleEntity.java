package com.boot.oauth.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 758790146350887375L;
	private String name;
	
	
}

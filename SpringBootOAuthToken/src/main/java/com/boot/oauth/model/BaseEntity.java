package com.boot.oauth.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5962319769775416318L;
	@Id
	@GeneratedValue
	private Integer id;

}

package com.example.greatlearning.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuditLog {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "date default sysdate")
    @Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Column
	private String description;

	@Column
	private Integer billValue;

	@Column
	private String error;
}

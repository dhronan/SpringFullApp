package com.sample.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@javax.persistence.Id
    private int id;
    private String name;
    private int qty;
    private long price;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="cusorder_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CusOrder cusorder;
    
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "created_on")
    @CreatedDate
    private Date createdOn;

    @Column(name = "updated_on")
    @LastModifiedDate
    private Date updatedOn;
}

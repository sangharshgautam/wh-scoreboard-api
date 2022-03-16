package com.sangharsh.oms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the user database table.
 *
 */
@Entity
@Table(name = "OMS_USER")
@Getter
@Setter
public class User extends BaseModel {

	private static final long serialVersionUID = 65981149772133526L;

	@Column(name = "PROVIDER_USER_ID")
	private String providerUserId;

	private String email;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	private String password;

	private String provider;

	// bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;
}

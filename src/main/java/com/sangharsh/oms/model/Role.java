package com.sangharsh.oms.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The persistent class for the role database table.
 *
 */
@Entity
@Table(name = "OMS_ROLE")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseModel {
	private static final long serialVersionUID = 1L;
	public static final String USER = "USER";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

	@Column
	private String name;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Role role = (Role) obj;
		if (!role.equals(role.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Role [name=").append(name).append("]").append("[id=").append(getId()).append("]");
		return builder.toString();
	}

    @Entity
    @Table(name = "scoreboard")
    @NoArgsConstructor
    @Getter
    @Setter
    @SuperBuilder
    public static class Scoreboad extends BaseModel {

        @OneToOne
        private Game game;

    }
}

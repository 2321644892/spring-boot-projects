package com.cheung.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户 实体类
 *
 * @author Cheung
 */
@Entity// 实体类
@Table(name = "jpa_user")// 表名
public class User implements Serializable {

	private static final long serialVersionUID = -8173961229317822066L;

	@Id// 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 主键生成策略
	@Column(name = "id")// 字段名
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "address")
	private String address;

	public User() {
	}

	public User(String name, Integer age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", address='" + address + '\'' +
				'}';
	}

}

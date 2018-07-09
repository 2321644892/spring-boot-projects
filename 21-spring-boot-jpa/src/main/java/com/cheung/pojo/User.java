package com.cheung.pojo;

import javax.persistence.*;

/**
 * 用户 实体类
 *
 * @author Cheung
 */
@Entity// 实体类
@Table(name = "jpa_user")// 表名
public class User {

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


	/**
	 * 用户:角色   n:1
	 *
	 * @ManyToOne 指定了多对一的关系，
	 *          cascadeType.PERSIST：级联新增（又称级联保存）
	 *          cascadeType.MERGE：级联合并（级联更新）
	 *          cascadeType.REMOVE：级联删除
	 *          cascadeType.REFRESH：级联刷新
	 *          cascadeType.ALL：以上四种都是
	 * @JoinColumn 表示用多的一方来维护外键，外键名称为"role_id"
	 * (注意：如果我们不通过JoinColum来指定外键的名称，系统会给我们声明一个名称)
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id")
	private Role role;


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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

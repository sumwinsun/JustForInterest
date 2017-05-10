
1. /*国内高速maven镜像*/
<mirror>
<id>alimaven</id>
<name>aliyun maven</name>
<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
<mirrorOf>central</mirrorOf>
</mirror>

		2. /*代码规范中常见的专业术语*/
		2.1 /*POJO对象*/
		PO(persistant object)	 	持久对象，同数据库字段；
		BO(business object) 	 	业务对象，封装对象、复杂对象 ，里面可能包含多个类；
		DTO(Data Transfer Object) 	数据传输对象，前端调用时传输 ；
		VO(value object)			表现对象，前端界面展示。
		DO(Domain Object)			领域对象，就是从现实世界中抽象出来的有形或无形的业务实体
		TO(Transfer Object)			数据传输对象，用于不同系统之间传递数据的对象

		2.2 /*hibernate中的对象*/
		2.2.1.Hibernate对象状态描述了对象在不同阶段的状态信息，而不是多个对象
		2.2.2.对象的状态共有三种
		瞬时状态：瞬时对象（TO） 不受Hibernate控制，不具有OID
		注意：TO对象不具有OID，一旦为TO赋值OID，那么此时就不是TO
		持久化状态：持久化对象（PO） 受Hibernate控制，具有OID
		托管状态：托管对象（DO） 不受Hibernate控制，具有OID
		2.2.3.	三种状态间切换时机
		TO
		new创建的对象并且不携带OID
		为DO对象设置OID为null
		PO
		save后的对象 update后的对象
		saveOrUpdate后的对象 merge后的对象
		delete后的对象
		load/get等通过查询方法获取到的对象
		Query Criteria读取的数据对应的对象
		DO
		Session关闭后，在此Session操作过程中所有的PO对象
		手工清除session中的某个PO（特殊）
		为TO对象指定OID
		2.2.4.TO，PO，DO的区别
		A.是否受控(Session)
		B.是否具有OID

		3. /*Java 8 接口类中允许有默认实现方法default和静态方法*/
private interface Defaulable {
	// jdk8中允许接口实现默认方法，你可以重写默认方法，也可以不重写
	default String notRequired() {
		return "Default implementation";
	}
}
private static class DefaultableImpl implements Defaulable {}
private static class OverridableImpl implements Defaulable {
	@Override
	public String notRequired() {
		return "Overridden implementation";
	}
}


4.  /*要想使某字段实现序列化，需要使用关键字 transient*/
transient String description;

		5.	/*list.subList(int fromIndex, int toIndex) 方法使用注意*/
		此方法返回的只是list的视图，无法进行强转操作；
		5.1修改此subList的结果，也会反馈在原List上

		6. 	/*map的推荐遍历方式*/
		Map<String, Integer> map = new HashedMap();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		map.put("4", 4);
		map.put("5", 5);
		map.entrySet();
		// 1.8 之前
		for (Map.Entry<String, Integer> x : map.entrySet()){
		System.out.println(x.getKey() + "-" + x.getValue());
		}
		// 1.8
		map.forEach((s, integer) -> System.out.println(s + integer));

		7. 	/*mybatis插入blob类型数据*/
		INSERT into test_01 VALUE (#{bytes,typeHandler=org.apache.ibatis.type.BlobTypeHandler})


		8.  /*ligerUI 部分*/
		8.1：ligerComboBox下拉框
		var selectData = [{id: "0", text: "请选择"}];
		var select = $('#borrowerId').ligerComboBox({data: "", isMultiSelect: false, width: 300});
		$.ajax({
		url:'/contract/getBorrowers',
		async: false,
		type: "post",
		success:function (data) {
		for (var i = 0; i < data.length; i++)
		selectData.push({id: data[i].userId, text: data[i].userName});
		select.setData(selectData);
		select.selectValue(0);
		}
		})
		8.2：

		9.	/*Spring事务管理*/
		http://blog.csdn.net/bao19901210/article/details/41724355
		// 编程式事务
		TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
		TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
		TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
		TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
		TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
		TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
		TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。
// 声明式事务
@Transactional(propagation = Propagation.NESTED)
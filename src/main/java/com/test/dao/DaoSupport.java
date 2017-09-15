package com.test.dao;

import com.test.bean.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository("userDao")
public class DaoSupport implements IUserDao {
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public User getUser(String name) {
		System.out.println("方法执行到了这里");
		User user=sqlSession.selectOne("IUserDao.getUser",name);
		return user;
	}

	@Override
	public int addMoney(int id, double money) {
		Map param=new HashMap();
		param.put("id",id);
		param.put("money",money);

		int result=sqlSession.update("IMoneyDao.addMoney",param);
		return result;
	}

	@Override
	public int subMoney(int id, double money) {
		Map param=new HashMap();
		param.put("id",id);
		param.put("money",money);

		int result=sqlSession.update("IMoneyDao.subMoney",param);
		return result;
	}

	@Override
	public void upUser(String name, String gender) {
		Map param=new HashMap();
		param.put("name",name);
		param.put("gender",gender);
		sqlSession.update("IUserDao.upUserByName",param);
	}

}

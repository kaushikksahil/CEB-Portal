/**
 * 
 */
package com.ceb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ceb.vo.CreateProject;

/**
 * @author Sahil Kaushik
 *
 */
public class CebDaoImpl implements ICebDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean addUserDetail(CreateProject object) {
		boolean status = false;
		Session session = sessionFactory.openSession();
		int id = (Integer) session.save(object);
		if (id == object.getProjectId()) {
			status = true;
			return status;
		} else {
			return false;
		}
	}

}

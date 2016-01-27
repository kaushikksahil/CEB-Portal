/**
 * 
 */
package com.ceb.service;

import java.util.Date;

import com.ceb.dao.ICebDao;
import com.ceb.vo.CreateProject;

/**
 * @author Sahil Kaushik
 *
 */
public class CebServiceImpl implements ICebService {

	ICebDao objectCebDao;

	public void setObjectCebDao(ICebDao objectCebDao) {
		this.objectCebDao = objectCebDao;
	}

	public boolean addUserDetail(CreateProject object) {
		Date date = new Date();
		boolean status = false;
		if (object.getProjectName().isEmpty()) {
			return false;
		} else if (object.getDeadlineDate().before(date)) {
			return false;
		} else {

			status = objectCebDao.addUserDetail(object);
			return status;
		}
	}

}

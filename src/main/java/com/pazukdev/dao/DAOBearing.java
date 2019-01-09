package com.pazukdev.dao;

import com.pazukdev.db.DataProvider;
import com.pazukdev.entities.OldBearing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAOBearing implements DAOInterface<OldBearing> {

	@Override
	public void create(OldBearing oldBearing) {
		EntityManager entityManager = DataProvider.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(oldBearing);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("DAOBearing create Exception", e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public OldBearing read(OldBearing oldBearing) {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			OldBearing b = em.find(OldBearing.class, oldBearing.getId());
			em.getTransaction().commit();
			return b;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing read Exception", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void update(OldBearing oldBearing) {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			OldBearing b = em.find(OldBearing.class, oldBearing.getId());
			b.setNumberOfOriginal(oldBearing.getNumberOfOriginal());
			b.setType(oldBearing.getType());
			b.setMajorLocation(oldBearing.getMajorLocation());
			b.setQuantity(oldBearing.getQuantity());
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing update Exception", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(OldBearing oldBearing) {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			OldBearing b = em.find(OldBearing.class, oldBearing.getId());
			em.remove(b);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing delete Exception", e);
		} finally {
			em.close();
		}
	}

	@Override
	public List<OldBearing> getList() {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<OldBearing> criteria = cb.createQuery(OldBearing.class);
			Root<OldBearing> root = criteria.from(OldBearing.class);
			criteria.select(root);
			List<OldBearing> list = em.createQuery(criteria).getResultList();
			em.getTransaction().commit();
			return list;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing getList Exception", e);
		} finally {
			em.close();
		}
	}

}

package edu.poly.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.domain.FavoriteReport;
import edu.poly.model.Favorite;

public class FavoriteDao extends AbstractEntityDao<Favorite> {

	public FavoriteDao() {
		super(Favorite.class);
		
	}
	public List<FavoriteReport> reportFavoritesByVideos(){
		String jpql ="select new edu.poly.domain.FavoriteReport(f.video.title, count(f),min(f.likedDate), max(f.likedDate))"
	    +"from Favorite f group by f.video.title";
		
		EntityManager  em = JpaUtils.getEntityManager();
		
		List<FavoriteReport> list = null;
		try {
			TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);
			list = query.getResultList();
		
		} finally {
			em.close();
		}
		return list;
	}
}

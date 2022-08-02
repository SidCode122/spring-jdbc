package com.coforge.training.spring_jdbc;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class CarsDao {
	
	private static final int select = 0;
	private static final String carId = null;
	private JdbcTemplate jtm; //Tool for simplifying programming with jdbc in spring framework.

	public void setJtm(JdbcTemplate jtm) {
		this.jtm = jtm;
	}
	/*
    *
    * For executing a query that select all rows from the table and returns a
    * list of domain model class objects,
    * you can use the BeanPropertyRowMapper class greatly simplifies the code
    */

	public List<Cars> getAllCars(){
		String sql="select * from cars";
		List<Cars> listCars=jtm.query(sql,BeanPropertyRowMapper.newInstance(Cars.class));
		return listCars;
	}
	public Cars findCarById(){
		String sql="select * from cars where id="+carId;
		Cars car=jtm.queryForObject(sql,BeanPropertyRowMapper.newInstance(Cars.class));
		return car;
	} 
	public int saveCars(Cars c)
	{
		String qry="Insert into cars(name,price) values('"+c.getName()+"','"+c.getPrice()+"')");
	return jtm.update(qry);
	}
	public void countCars()
	{
		String sql="select * from cars ";
		int n=jtm.queryForObject(sql,Integer.class);
		System.out.println("Total numbers of cars :"+n);
	}
	public int updateCar(Cars c)
    {
        String qry="update cars set price='"+c.getPrice()+"' where id='"+c.getId()+"'";
        return jtm.update(qry);
    }
   
    public int deleteCar(Cars c)
    {
        String qry= "delete from cars where id='"+c.getId()+"'";
        return jtm.update(qry);
    }
}

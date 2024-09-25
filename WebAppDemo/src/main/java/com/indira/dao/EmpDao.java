package com.indira.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.indira.entity.Employee;
import com.indira.utility.ConnectionFactory;

public class EmpDao {
	public boolean insert(Employee e) {
		try {
			Connection con = ConnectionFactory.getCon();
			PreparedStatement ps = con.prepareStatement("INSERT INTO EMP values(?,?,?)");
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setDouble(3, e.getSalary());
			int result = ps.executeUpdate();
			if (result == 1) {
				return true;
			}

		} catch (Exception en) {
			en.printStackTrace();
		}
		return false;
	}

	public boolean update(double sal, int id) {
		try {
			Connection con = ConnectionFactory.getCon();
			// Step3: Create PreparedStatement for Update
			PreparedStatement ps = con.prepareStatement("Update emp set salary=? where id=?");
			ps.setDouble(1, sal);
			ps.setInt(2, id);

			// Step 4: Execute the INSERT query
			int result = ps.executeUpdate();
			if (result == 1) {
				return true;
			}

		} catch (Exception en) {
			en.printStackTrace();
		}
		return false;

	}

	public Employee select(int id) {
		try {
			Connection con = ConnectionFactory.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP WHERE ID=?");
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				int i = result.getInt("id");
				String name = result.getString("name");
				double sal = result.getDouble("salary");
				Employee e = new Employee(i, name, sal);
				System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary());
				return e;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Employee> SelectAll() {
		try {
			Connection con = ConnectionFactory.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP");
			ResultSet result = ps.executeQuery();
			List<Employee> list = new ArrayList<>();
			while (result.next()) {
				int i = result.getInt("id");
				String name = result.getString("name");
				double sal = result.getDouble("salary");
				System.out.println(i + " " + name + " " + sal);
				Employee e = new Employee(i, name, sal);
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(int id) {
		Connection con = ConnectionFactory.getCon();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM EMP WHERE id=?");
			ps.setInt(1, id);

			int result = ps.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

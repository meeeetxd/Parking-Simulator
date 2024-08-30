package DAO;
import java.sql.*;
import java.util.*;

import Model.Slot;
//import Model.User;
import Utility.Database;

public class SlotDAO {
	private Connection connection;
	
	public SlotDAO() {
		connection = Database.getConnection();
	}
	
	public boolean addSlot(Slot slotNumber) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Slots(number, status) values(?,?)");
			stmt.setString(1, slotNumber.getNumber());
			stmt.setString(2, slotNumber.getStatus());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
//	-------------------New Slot Function-------------------
	public void addSlotNew(String slotNumber) {
        try {
            String sql = "INSERT INTO Slots (number) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, slotNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//	-------------------New Slot Function-------------------	
	public boolean deleteSlot(Slot slot) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from Slots where number = ?");
			stmt.setString(1, slot.getNumber());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Slot> getAvailableSlots(){
		List<Slot> slots = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from Slots where status = 'available'");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Slot slot = new Slot();
				slot.setId(rs.getInt("id"));
				slot.setNumber(rs.getString("number"));
				slot.setStatus(rs.getString("status"));
				slots.add(slot);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return slots;
	}
	
	
//	---------------------New Function for getting slots ---------------------
	
	public List<Slot> getAllSlots() {
        List<Slot> slots = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Slots";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot();
                slot.setId(rs.getInt("id"));
                slot.setNumber(rs.getString("number"));
                slot.setStatus(rs.getString("status"));
                slots.add(slot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }
//	---------------------New Function for getting slots ---------------------
}

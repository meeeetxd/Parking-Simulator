package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Booking;
import Utility.Database;

public class BookingDAO {
	private Connection connection;
	
	public BookingDAO() {
		connection = Database.getConnection();
	}
	
	public boolean bookSlot(Booking booking) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Bookings(user_id, slot_id, start_time, end_time, status, payment_status) values (?,?,?,?,?,?)");
			stmt.setInt(1,  booking.getUserId());
			stmt.setInt(2,  booking.getSlotId());
			stmt.setString(3, booking.getStartTime());
			stmt.setString(4, booking.getEndtime());
			stmt.setString(5, booking.getStatus());
			stmt.setString(6, booking.getPaymentStatus());
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {
				PreparedStatement stmt2 = connection.prepareStatement("update Slots SET status = 'booked' where id = ?");
				stmt2.setInt(1, booking.getSlotId());
				stmt2.executeUpdate();
				
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Booking> getUserBookings(int userId){
		List<Booking> bookings = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement("Select * from Bookings where user_id =?");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt("id"));
				booking.setUserId(rs.getInt("user_id"));
				booking.setSlotId(rs.getInt("slot_id"));
				booking.setStartTime(rs.getString("start_time"));
				booking.setEndtime(rs.getString("end_time"));
				booking.setStatus(rs.getString("status"));
				booking.setPaymentStatus(rs.getString("payment_status"));
				bookings.add(booking);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}
}

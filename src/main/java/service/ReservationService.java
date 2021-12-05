package service;

import model.*;

import java.sql.SQLException;
import java.util.*;

public final class ReservationService {
    private static ReservationService instance;
    private static Map<String, IRoom> rooms;
    private static Map<String, Reservation> reservations;
    private static Map<Date, HashSet<String>> occupiedRooms;
    private static Map<String, HashSet<String>> customersReservations;

    private ReservationService() {
        rooms = new HashMap<>();
        reservations = new HashMap<>();
        occupiedRooms = new HashMap<>();
        customersReservations = new HashMap<>();
    }

    public static ReservationService getInstance() {
        if(instance == null)
            instance = new ReservationService();
        return instance;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public Reservation getAReservation(String reservationId){
        if(reservations.containsKey(reservationId))
            return reservations.get(reservationId);
        System.out.printf("Reservation with id '%s' was not found.", reservationId);
        return null;
    }

    public IRoom getARoom(String roomId){
        if(rooms.containsKey(roomId))
            return rooms.get(roomId);
        System.out.printf("Room with id '%s' was not found.", roomId);
        return null;
    }

    public Collection<IRoom> getAllRooms() {
        Database db = new Database();
        db.connect();
        String sql = "SELECT * FROM Room";
        try{
            db.statement = db.connection.createStatement();
            db.result = db.statement.executeQuery(sql);

            while(db.result.next()){
                String roomNumber = db.result.getString("roomNumber");
                RoomType roomType = RoomType.getOption(db.result.getInt("roomType"));
                Double price = db.result.getDouble("price");
                Room roomTemp = new Room(roomNumber, price, roomType);
                System.out.println("Room Number = " + roomTemp.getRoomNumber());
                System.out.println("Room Type = " + roomTemp.getRoomType());
                System.out.println("Price  = " + roomTemp.getRoomPrice());
                System.out.println("------------------------------");
                rooms.put(roomTemp.getRoomNumber(), roomTemp);
            }
        }catch (SQLException e){
            System.out.println("Operation Error: " + e.getMessage());
        }finally {
            try {
                db.connection.close();
                db.statement.close();
                db.result.close();
            }catch (SQLException e){
                System.out.println("Error to close the connection: " + e.getMessage());
            }
        }
        return rooms.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        String reservationId = String.valueOf(reservations.size() + 1);
        Reservation reservation = new Reservation(reservationId, customer, room, checkInDate, checkOutDate);
        reservations.put(reservationId, reservation);
        this.updateOccupiedRooms(room.getRoomNumber(), checkInDate, checkOutDate);
        this.updateCustomersReservations(customer.getCustomerEmail(), reservationId);
        return reservation;
    }

    public void updateOccupiedRooms(String roomId, Date checkInDate, Date checkOutDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(checkInDate);
        Calendar end = Calendar.getInstance();
        end.setTime(checkOutDate);

        for(Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if(!occupiedRooms.containsKey(date)){
                HashSet<String> roomsId = new HashSet<>();
                occupiedRooms.put(date, roomsId);
            }
            HashSet<String> roomsId = occupiedRooms.get(date);
            roomsId.add(roomId);
        }
    }

    public void updateCustomersReservations(String customerEmail, String reservationId) {
        if(!customersReservations.containsKey(customerEmail)){
            HashSet<String> reservationsId = new HashSet<>();
            customersReservations.put(customerEmail, reservationsId);
        }
        HashSet<String> reservationsId = customersReservations.get(customerEmail);
        reservationsId.add(reservationId);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(checkInDate);
        Calendar end = Calendar.getInstance();
        end.setTime(checkOutDate);

        HashSet<String> availableRoomsId = new HashSet<>(rooms.keySet());

        for(Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if(!occupiedRooms.containsKey(date))
                continue;
            HashSet<String> occupiedRoomsId = occupiedRooms.get(date);
            availableRoomsId.removeAll(occupiedRoomsId);
            if(availableRoomsId.isEmpty())
                break;
        }

        Collection<IRoom> availableRooms = new ArrayList<>();
        for(String roomId: availableRoomsId)
            availableRooms.add(rooms.get(roomId));
        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> reservations = new ArrayList<>();
        if(!customersReservations.containsKey(customer.getCustomerEmail()))
            return reservations;
        for(String reservationId: customersReservations.get(customer.getCustomerEmail())) {
            Reservation reservation = this.getAReservation(reservationId);
            reservations.add(reservation);
        }
        return reservations;
    }

    public void printAllRooms() {
        getAllRooms();
        for(IRoom room: rooms.values())
            System.out.println(room);
    }

    public void printAllReservation() {
        for(Reservation reservation: reservations.values())
            System.out.println(reservation);
    }
}

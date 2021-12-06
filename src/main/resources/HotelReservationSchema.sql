DROP DATABASE IF EXISTS HotelReservationDB;
CREATE DATABASE HotelReservationDB;
USE HotelReservationDB;

CREATE TABLE Customer(
	customerEmail VARCHAR(40) NOT NULL PRIMARY KEY,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL
);

CREATE TABLE Room(
	roomNumber VARCHAR(20) NOT NULL PRIMARY KEY,
    roomType ENUM('1','2') NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE Reservation(
	reservationId VARCHAR(20) NOT NULL PRIMARY KEY,
    checkInDate DATE,
    checkOutDate DATE,
    customerEmail VARCHAR(40),
    roomNumber VARCHAR(20),
    CONSTRAINT fkReservationCustomer
    FOREIGN KEY (customerEmail) REFERENCES Customer(customerEmail) ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fkReservationRoom
    FOREIGN KEY (roomNumber) REFERENCES Room(roomNumber) ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
INSERT INTO Customer(customerEmail, firstName, lastName) VALUES
('lucas@gmail.com', 'Lucas', 'Graciano'),
('andre@outlook.com', 'André', 'Ramos'),
('flavio@yahoo.com', 'Flávio', 'Bergamini'),
('renzo@hotmail.com', 'Renzo', 'Mesquita'),
('joaopedro@huawei.com', 'João', 'Graciano');

INSERT INTO Room(roomNumber, roomType, price) VALUES
('101', '1', 200),
('102', '1', 130),
('201', '1', 210),
('202', '2', 240),
('301', '2', 240),
('302', '2', 300);

# Cliente sem reserva											OK (joaopedro@huawei.com)
# Cliente com apenas 1 reserva									OK (flavio@yahoo.com)
# Cliente com 2 reservas para a mesma Data						OK (renzo@hotmail.com
# Cliente com 2 reservas com datas diferentes                   OK (lucas@gmail.com)
# Quarto sem reserva                                            OK (102)
# Quarto com 2 reservas                                         OK (202)
# Quarto com checkOut e checkIn na mesma Data                   OK (201)

INSERT INTO Reservation(reservationId, checkInDate, checkOutDate, customerEmail, roomNumber) VALUES
('762BSDI', '2021-12-05', '2021-12-07', 'lucas@gmail.com', '202'),
('363DN62', '2022-03-01', '2022-03-05', 'flavio@yahoo.com', '302'),
('H9F429H', '2021-12-18', '2021-12-19', 'lucas@gmail.com', '301'),
('09V8HV4', '2022-01-05', '2022-01-15', 'renzo@hotmail.com', '201'),
('89H2398', '2022-01-15', '2022-01-20', 'andre@outlook.com', '201'),
('98H4VFF', '2022-02-14', '2022-02-18', 'renzo@hotmail.com', '202'),
('787T8EH', '2022-01-05', '2022-01-15', 'renzo@hotmail.com', '101'),
('98Y43B3', '2021-12-25', '2021-12-27', 'andre@outlook.com', '302'),
('8CW9C3N', '2021-12-27', '2022-01-04', 'lucas@gmail.com', '302'),
('UBW7249', '2022-01-04', '2022-01-07', 'lucas@gmail.com', '301');

SELECT * FROM Customer;
SELECT * FROM Room;
SELECT * FROM Reservation;
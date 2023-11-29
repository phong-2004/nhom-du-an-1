create database QLNS1
use QLNS1
create table NhanVien
(
	MaNV nvarchar(20) primary key,
	HoVaTen nvarchar(50),
	Email nvarchar(50),
	SDT nvarchar (15),
	cccd nvarchar(15),
	NgaySinh date,
	GioiTinh bit,
	MatKhau nvarchar(50),
	diachi nvarchar(150),
	anh nvarchar(300),
	BacLuong nvarchar(50),
	MaPB nvarchar(20),
	foreign key(BacLuong) references Luong(BacLuong),
	foreign key(MaPB) references PhongBan(MaPB)
)


create table quanly
(
	MaNV nvarchar(20) primary key,
	HoVaTen nvarchar(50),
	Email nvarchar(50),
	SDT nvarchar (15),
	cccd nvarchar(15),
	NgaySinh date,
	GioiTinh bit,
	MatKhau nvarchar(50),
	diachi nvarchar(150),
	anh nvarchar(300),
	BacLuong nvarchar(50),
	MaPB nvarchar(20),
	foreign key(BacLuong) references Luong(BacLuong),
	foreign key(MaPB) references PhongBan(MaPB)
)
create table Luong
(
	BacLuong nvarchar(50) primary key,
	LuongCoBan float,
	hesoluong float,
	HeSoPhuCap float
)
create table PhongBan
(
	MaPB nvarchar(20) primary key,
	TenPB nvarchar(150),
	SDT float
)
create table BNS
(
	MaNV nvarchar(20) primary key,
	HoVaTen nvarchar(50),
	Email nvarchar(50),
	SDT nvarchar (15),
	cccd nvarchar(15),
	NgaySinh date,
	GioiTinh bit,
	MatKhau nvarchar(50),
	diachi nvarchar(150),
	anh nvarchar(300),
	BacLuong nvarchar(50),
	MaPB nvarchar(20),
	foreign key(BacLuong) references Luong(BacLuong),
	foreign key(MaPB) references PhongBan(MaPB)
)


INSERT INTO BNS (MaNV, HoVaTen, Email, SDT, cccd, NgaySinh, GioiTinh, MatKhau, diachi, anh, BacLuong, MaPB)
VALUES ('BNS001', 'Le Van C', 'c@example.com', '135792468', '135792468', '1988-08-08', 1, 'pass789', '789 CDE Street', 'D:\tong\java3\ASMJAVA3\IMG_Cat\anh-meo-cute.jpg', 'BL001', 'PB003');


INSERT INTO Luong (BacLuong, LuongCoBan, hesoluong, HeSoPhuCap)
VALUES ('BL001', 10000000, 1.2, 0.1);


INSERT INTO PhongBan (MaPB, TenPB, SDT) VALUES ('PB001', N'Phòng Kinh Doanh', 0386822121);
INSERT INTO PhongBan (MaPB, TenPB, SDT) VALUES ('PB002', N'Phòng Marketing', 0386822121);
INSERT INTO PhongBan (MaPB, TenPB, SDT) VALUES ('PB004', N'Phòng IT', 0386822121);
INSERT INTO PhongBan (MaPB, TenPB, SDT) VALUES ('PB005', N'Phòng Tài Chính', 0386822421);
INSERT INTO PhongBan (MaPB, TenPB, SDT)VALUES ('PB003', 'Phong Ban A', 123456789);


INSERT INTO quanly (MaNV, HoVaTen, Email, SDT, cccd, NgaySinh, GioiTinh, MatKhau, diachi, anh, BacLuong, MaPB)
VALUES ('QL001', 'Tran Thi B', 'b@example.com', '987654321', '987654321', '1985-05-05', 0, 'pass456', '456 XYZ Street', 'D:\tong\java3\ASMJAVA3\IMG_Cat\2.jpg', 'BL001', 'PB003');



INSERT INTO NhanVien (MaNV, HoVaTen, Email, SDT, cccd, NgaySinh, GioiTinh, MatKhau, diachi, anh, BacLuong, MaPB)
VALUES ('NV001', 'Nguyen Van A', 'a@example.com', '123456789', '123456789', '1990-01-01', 1, 'pass123', '123 ABC Street', 'D:\tong\java3\ASMJAVA3\IMG_Cat\2.jpg', 'BL001', 'PB003');
INSERT INTO NhanVien (MaNV, HoVaTen, Email, SDT, cccd, NgaySinh, GioiTinh, MatKhau, diachi, anh, BacLuong, MaPB)
VALUES ('NV003', 'Lê Duy Phong', 'ldphongl10ha@gmail.com', '0386822121', '000000000000', '1990-01-01', 1, 'Phong12345678', 'Hoài Ân', 'D:\tong\java3\ASMJAVA3\IMG_Cat\123.jpg', 'BL001', 'PB004');




INSERT INTO NhanVien (MaNV, HoVaTen, Email, SDT, cccd, NgaySinh, GioiTinh, MatKhau, diachi, anh, BacLuong, MaPB)
VALUES ('NV002', 'Nguyen Van c', 'a@example.com', '123456789', '123456789', '1990-01-01', 1, 'pass123', '123 ABC Street', 'D:\tong\java3\ASMJAVA3\IMG_Cat\anh-meo-cute.jpg', 'BL001', 'PB003');

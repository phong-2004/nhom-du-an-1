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

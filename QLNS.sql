create database QLNS
use QLNS
create table NhanVien
(
	MaNV nvarchar(20) primary key,
	MatKhau nvarchar(50),
	HoVaTen nvarchar(50),
	GioiTinh bit, 
	QueQuan nvarchar(150),
	SDT float,
	Email nvarchar(50),
	NgaySinh date,
	ChucVu nvarchar(50),
	BacLuong nvarchar(50),
	MaPB nvarchar(20),
	foreign key(BacLuong) references Luong(BacLuong),
	foreign key(MaPB) references PhongBan(MaPB)
)

create table Luong
(
	BacLuong nvarchar(50) primary key,
	LuongCoBan float,
	HeSoLuong float,
	HeSoPhuCap float
)

create table PhongBan
(
	MaPB nvarchar(20) primary key,
	TenPB nvarchar(150),
	DiaChi nvarchar(250),
	SDT float
)

create table BanNhanSu
(
	MaBNS nvarchar(50) primary key,
	MatKhau nvarchar(50),
	HoTen nvarchar(50),
)

insert into BanNhanSu(MaBNS, MatKhau, HoTen)
values('BN1', '123456', 'Nguyễn Văn A');


insert into Luong (BacLuong, LuongCoBan, HeSoLuong, HeSoPhuCap)
values
('Nhân viên chính thức', 7000000, 1.0, 0.2);


INSERT INTO PhongBan (MaPB, TenPB, DiaChi, SDT)
VALUES
('PB01', 'Phòng Kinh doanh', '123 Nguyễn Văn Cừ, Q.1, TP.HCM', 84822112345);

INSERT INTO NhanVien (MaNV, MatKhau, HoVaTen, GioiTinh, QueQuan, SDT, Email, NgaySinh, ChucVu, BacLuong, MaPB)
VALUES
('NV01', '123456', 'Nguyễn Văn A', 1, 'TP.HCM', 84822112345, 'nguynvana@gmail.com', '1990-01-01', 'Nhân viên kinh doanh', 'Nhân viên chính thức', 'PB01');





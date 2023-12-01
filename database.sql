USE [quanlybanhang]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[MaHD] [nvarchar](50) NOT NULL,
	[MaHang] [nvarchar](50) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[DonGia] [decimal](9, 3) NOT NULL,
	[ThanhTien] [decimal](9, 3) NULL,
 CONSTRAINT [PK_ChiTietHoaDon] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC,
	[MaHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDonBan]******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonBan](
	[MaHD] [nvarchar](50) NOT NULL,
	[NgayTao] [smalldatetime] NULL,
	
 CONSTRAINT [PK_HoaDonBan] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MatHang] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MatHang](
	[MaHang] [nvarchar](50) NOT NULL,
	[TenHang] [nvarchar](50) NULL,
	[SoLuong] [decimal](9, 1) NULL,
	[GiaNhap] [decimal](9, 1) NULL,
	[GiaBanLe] [decimal](9, 1) NULL,
	[DonViTinh] [nvarchar](50) NULL,
	[GhiChu] [nvarchar](100) NULL,
	[NgayKhoiTao] [date] NULL,
 CONSTRAINT [PK_MatHang] PRIMARY KEY CLUSTERED 
(
	[MaHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_HoaDonBan] FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDonBan] ([MaHD])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_HoaDonBan]
GO

ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_MatHang] FOREIGN KEY([MaHang])
REFERENCES [dbo].[MatHang] ([MaHang])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_MatHang]
GO

INSERT INTO dbo.MatHang
		(MaHang,
		TenHang,
		SoLuong,
		GiaNhap,
		GiaBanLe,
		DonViTinh,
		GhiChu,
		NgayKhoiTao
		)
VALUES ('111','Mi Goi',4,10,12,'Goi',NULL,NULL),
('112','Gao',4,10,12,'Goi',NULL,NULL);

INSERT INTO dbo.HoaDonBan
		(
		MaHD,
		NgayTao
		)
VALUES ('100','1/10/2023')

INSERT INTO dbo.ChiTietHoaDon
		(
		MaHD,
		MaHang,
		SoLuong,
		DonGia,
		ThanhTien
		)
VALUES ('100','111',1,15,15)


 /* DROP DATABASE [quanlybanhang]  */

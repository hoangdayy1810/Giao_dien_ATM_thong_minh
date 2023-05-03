# Giao diện ATM thông minh
Project của học phần Công nghệ Java

Nhóm thực hiện: Nhóm 2

## Giới thiệu 
ATM được sử dụng rộng rãi và phổ biến trong thế giới hiện đại ngày nay.
Vì vậy, với giao diện ATM thông minh sẽ giúp cho người dùng có khả năng tương tác và sử dụng trở nên dễ dàng hơn.
Chương trình được sử dụng ngôn ngữ lập trình Java

![image](https://user-images.githubusercontent.com/95036673/235832338-77e3384d-d6a8-4c3f-9741-8bc8144bac8e.png)

## Các chức năng có trong hệ thống
- Liên kết tới cơ sở dữ liệu
- Đăng nhập
- Đăng kí
- Nạp tiền
- Rút tiền
- Chuyển tiền
- Lịch sử giao dịch
- Đổi mật khẩu
- Khóa tài khoản

## Phần mềm và công cụ
- [Java JDK 8+](https://www.oracle.com/java/technologies/downloads/#jdk19-windows)
- [Eclipse](https://www.eclipse.org/downloads/)
- [SQL Server Management Studio Management Studio 19](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)

## Hướng dẫn cài đặt

- File code: có thể tải ở bên trên hoặc vào mục **Releases** -> **Assets** bấm vào **Source code(zip)** để tải
- File báo cáo PDF, file SQL, hình ảnh nằm ở mục **Releases** bấm vào **BTL.zip** để tải

Sau khi đã tải xong những file cần thiết, đầu tiên

### Khởi tạo database
Bước 1: Mở SQL Server Mangement Studio Management Studio 19. Ấn **Connect** để kết nối tới máy tính của bạn

![image](https://user-images.githubusercontent.com/95036673/235887748-ec0fe8d0-287a-430c-ba45-55e7d17cf0f0.png)


Bước 2: Tìm file **BTL.zip** trích xuất thành folder rồi tiến hành chọn **File** -> **Open** -> **File...** -> **BTL** -> chọn file **BankAccount.sql**

![image](https://user-images.githubusercontent.com/95036673/235888591-a2955694-9b83-4db5-8293-d8dc4f225bf9.png)


Bước 3: Chạy từng phần có trong file: tạo cơ sở dữ liệu, tạo bảng, các hàm thủ tục,...

![image](https://user-images.githubusercontent.com/95036673/235889236-64faa19b-17ed-4af6-be73-a5c568570870.png)


Tiếp theo, ta sẽ chạy code trên Eclipse
### Khởi động hệ thống bằng Eclipse
**Cách 1: Chạy trực tiếp bằng link Git**

Bước 1: Mở Eclipse 

Bước 2: Chọn **File** -> **Import** -> **Git** -> **Projects From Git(with smart import)** -> **Clone Uri** -> Copy URL vào trường URL: 
(https://github.com/hoangdayy1810/Giao_dien_ATM_thong_minh) -> chọn master **Branch** -> **Next** -> **Next** -> **Next** -> **Finish**

Bước 3: Chọn vào project, nhấn chuột phải chọn **Build Path** -> **Configure Build Path**

Bước 4: Chạy hàm main trong **BankAccount** -> **src** -> **GUI** -> **Main_Start.java**

**Cách 2: Chạy bằng file nén**

Bước 1: Giải nén file **Giao_dien_ATM_thong_minh-Giao_dien_ATM_thong_minh.zip** 

Bước 2: Vào Eclipse, chọn **File** -> **Open Projects from File Systems...** -> bấm vào nút **Directory...** -> tìm file đã giải nén -> **Finish**

Bước 3: Chạy hàm main trong **BankAccount** -> **src** -> **GUI** -> **Main_Start.java**

# Một số hình ảnh về projects

![image](https://user-images.githubusercontent.com/95036673/235897902-888d7eb1-1f2d-419c-803e-587ab045f56b.png)

![image](https://user-images.githubusercontent.com/95036673/235897945-3c28ccab-8aa0-436d-9134-16622a85021d.png)

![image](https://user-images.githubusercontent.com/95036673/235897976-96731320-c9d8-446c-8bc6-bb6089299777.png)

![image](https://user-images.githubusercontent.com/95036673/235898008-63a80ddd-6303-4f0c-9a22-dc7d74f9743e.png)

# Lời kết

Sản phẩm còn chưa được hoàn thiện, nhóm sẽ cố gắng nâng cấp và sửa chữa

Xin gửi lời cảm ơn chân thành tới giảng viên bộ môn này

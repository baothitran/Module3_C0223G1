-- EXISTS, ANY, ALL: Đây là các từ khóa được sử dụng trong các truy vấn điều kiện để so sánh giá trị trong
-- các bảng hoặc các truy vấn con.
-- EXISTS: Kiểm tra xem một truy vấn con có trả về bất kỳ kết quả nào không.
-- ANY: So sánh một giá trị với một danh sách giá trị được trả về bởi một truy vấn con và trả về TRUE 
-- nếu giá trị bằng bất kỳ giá trị nào trong danh sách đó.
-- ALL: So sánh một giá trị với một danh sách giá trị được trả về bởi một truy vấn con và trả về TRUE 
-- nếu giá trị bằng tất cả các giá trị trong danh sách đó.

-- Từ khóa LIKE với "%' và "_": Từ khóa LIKE trong SQL được sử dụng để tìm kiếm các giá trị trong một 
-- cột dựa trên một mẫu chuỗi.
-- "%": Đại diện cho bất kỳ ký tự nào hoặc không có ký tự nào. Ví dụ: "abc%" sẽ tìm kiếm tất cả các giá 
-- trị bắt đầu bằng "abc".
-- "_": Đại diện cho một ký tự bất kỳ. Ví dụ: "a_c" sẽ tìm kiếm tất cả các giá trị có chữ "a" ở vị trí đầu 
-- tiên, một ký tự bất kỳ ở vị trí thứ hai và chữ "c" ở vị trí cuối cùng.

-- INSERT SELECT: Là cú pháp SQL được sử dụng để chèn dữ liệu từ một bảng vào một bảng khác.
-- INSERT INTO [table1] SELECT [column1], [column2] FROM [table2]: Sẽ chèn dữ liệu từ cột "column1" và 
-- "column2" trong bảng "table2" vào bảng "table1".

-- UNION trong SQL là gì: UNION là một phép toán trong SQL được sử dụng để kết hợp các tập dữ liệu từ 
-- các bảng khác nhau thành một tập dữ liệu duy nhất.
-- Cú pháp: SELECT [column1], [column2] FROM [table1] UNION SELECT [column1], [column2] FROM [table2]: 
-- Sẽ kết hợp tất cả các giá trị của cột "column1" và "column2" từ hai bảng "table1" và "table2" thành 
-- một tập dữ liệu duy nhất. Lưu ý rằng các cột trong các truy vấn UNION phải có cùng kiểu dữ liệu và 
-- số lượng cột tương đương.
# --- CẤU HÌNH CƠ BẢN ---
-ignorewarnings
-dontwarn
-dontnote

# --- LÀM PHẲNG GÓI (QUAN TRỌNG ĐỂ ẨN CẤU TRÚC) ---
# Dời tất cả các class về thư mục gốc (default package) để giấu cấu trúc thư mục gốc
-repackageclasses ''
-flattenpackagehierarchy ''

# --- TỐI ƯU HÓA VIỆC ĐỔI TÊN ---
# Cho phép ProGuard mở rộng quyền truy cập (public/private) để đổi tên được nhiều hơn
-allowaccessmodification

# Tận dụng tối đa việc đặt tên trùng nhau (Overloading) để làm code khó đọc hơn
-overloadaggressively

# Không sử dụng tên class dạng hỗn hợp (giúp file nhỏ hơn một chút)
-dontusemixedcaseclassnames

# --- XÓA THÔNG TIN GỠ LỖI (ẨN HOÀN TOÀN) ---
# Xóa bảng dòng (Line number) và tên biến cục bộ -> Khi decompile sẽ không thấy tên biến gốc
-keepattributes !LineNumberTable,!LocalVariableTable,!SourceFile,!*Annotation*
-keep class com.mbbank.alexherry.chamaychet { *; }

# Đổi tên file nguồn thành chuỗi rỗng để giấu tên file gốc
-renamesourcefileattribute ''

# --- TỐI ƯU HÓA CODE ---
-optimizationpasses 5
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

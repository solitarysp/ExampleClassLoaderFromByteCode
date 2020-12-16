# Repo demo việc loader class từ file byte code.

    - Không bắt buộc phải là file byte code. Có thể byte code được lưu trong database...etc...

## Ý nghĩa các file

```
\---com
    \---lethanh98
        \---example
            \---ExampleClassLoaderFromByteCode
                |   Main.java // Main test sử dụng loader bytecode sau đó new instance và sử dụng class đó
                |   MainCreateClassByteCode.java // Main demo tạo file byte code
                |   
                +---authen
                |       Base.java # Bởi vì để chạy 1 class tại thời điểm Runtime. Chúng ta sẽ cần có 1 interface để định nghĩa. Các class muốn chạy sử dụng loader byte code thì cần impl interface này
                |       DefaultBaseImpl.java # Class impl Base và muốn sử dụng loader byte code .
                |       
                \---ultils
                        CustomClassLoader.java # class impl logic load bởi byte code
```

## Step chạy

     - 1 : Chạy MainCreateClassByteCode trước để tạo ra file byte code
     - 2 : Comment tất cả file DefaultBaseImpl.java để chạy Main sẽ không còn file này
     - 3 : Chạy Main
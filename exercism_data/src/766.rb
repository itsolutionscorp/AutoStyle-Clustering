def compute(str1,str2)
    
    hamming_count = 0
    a = str1.chars
    b = str2.chars
    length = (a.count < b.count) ? a.count : b.count
    
    length.times {|l| hamming_count += 1 if a[l] != b[l] }

    hamming_count
  end
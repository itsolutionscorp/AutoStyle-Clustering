def compute(str1, str2) 
       str1.chars.take(str2.length).zip(str2.chars).select {|a, b| a != b}.length
    end
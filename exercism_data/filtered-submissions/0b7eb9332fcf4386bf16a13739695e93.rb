def compute(str1,str2)
    error_count = 0
    index = 0
    while index < str1.length and index < str2.length
      if str1[index] != str2[index]
        error_count += 1
      end
      index += 1
    end
    error_count
  end
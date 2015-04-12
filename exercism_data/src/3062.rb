def compute(str1, str2)
    return nil unless str1.size == str2.size
    str1.size.times.count { |i| str1[i] != str2[i] }
  end
def compute(str1, str2)
    str1.chars.zip(str2.chars).count { |a, b| a != b }
  end
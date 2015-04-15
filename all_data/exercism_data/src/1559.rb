def compute(str1, str2)
    (0...([str1.length, str2.length].max)).each.count { |x| str1[x] != str2[x] }
  end
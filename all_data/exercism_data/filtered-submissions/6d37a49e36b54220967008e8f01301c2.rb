def compute(str1, str2)
    return 0 if str1 == str2

    [str1.length, str2.length].min.times
      .count { |i| str1[i] != str2[i] }
  end
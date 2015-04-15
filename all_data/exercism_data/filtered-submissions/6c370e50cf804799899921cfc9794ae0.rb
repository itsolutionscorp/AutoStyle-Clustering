def compute(str1, str2)
    count = 0
    str_len = [str1, str2].min_by(&:size).size
    str_len.times { |i| count += 1 if str1[i] != str2[i] }
    count
  end
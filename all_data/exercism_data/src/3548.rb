def compute(str1, str2)
    side_by_side_letters = str1.chars.zip(str2.chars)
    side_by_side_letters.reduce(0) { |x| x[0] == x[1]? 0 : 1 }
  end
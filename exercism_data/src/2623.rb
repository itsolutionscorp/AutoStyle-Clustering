def compute(str_one, str_two)
    len = [str_one.size, str_two.size].min
    hamming = 0
    (0...len).each { |i| hamming += 1 if str_one[i] != str_two[i] }
    hamming
  end
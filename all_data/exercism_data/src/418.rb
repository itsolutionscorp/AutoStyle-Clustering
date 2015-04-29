def compute(s1, s2)
    distance = 0
    [s1.length, s2.length].min.times { |d| distance += 1 unless s1[d] == s2[d] }
    distance
  end
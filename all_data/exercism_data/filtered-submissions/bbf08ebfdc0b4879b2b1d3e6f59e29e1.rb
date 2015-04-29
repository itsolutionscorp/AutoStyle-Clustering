def compute(s1, s2)
    shorter_size = [s1.size, s2.size].min
    shorter_size.times.count { |i| s1[i] != s2[i] }
  end
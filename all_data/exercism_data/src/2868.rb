def compute(s1, s2)
    min_size = [s1, s2].map(&:size).min
    min_size.times.count { |i| s1[i] != s2[i] }
  end
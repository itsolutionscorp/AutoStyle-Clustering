def compute s1, s2
    [s1.length, s2.length].min.times.count { |i| s1[i] != s2[i] }
  end
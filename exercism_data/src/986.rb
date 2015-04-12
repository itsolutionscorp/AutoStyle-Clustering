def compute(s1, s2)
    [s1.length, s2.length].max.times.count {|n| s1[n] != s2[n]}
  end
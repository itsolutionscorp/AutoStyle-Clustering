def compute(s1, s2)
    diff = 0
    [s1.length, s2.length].min.times do |i|
      diff += 1 if s1[i] != s2[i]
    end
    diff
  end
def compute(s1, s2)
    differences = 0

    [s1.length, s2.length].min.times do |i|
      if s1[i] != s2[i] then differences += 1 end
    end

    return differences
  end
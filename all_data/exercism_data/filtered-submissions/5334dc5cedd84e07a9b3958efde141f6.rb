def compute(s1, s2)
    distance = 0

    (0..[s1.length, s2.length].min).each do |i|
      if s1[i] != s2[i]
        distance += 1
      end
    end

    return distance
  end
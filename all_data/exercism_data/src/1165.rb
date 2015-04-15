def compute(s1, s2)
    distance = 0
    unless s1 == s2
      (0...[s1.length, s2.length].min).each do |i| 
        distance += 1 if s1[i] != s2[i] 
      end
    end

    distance
  end
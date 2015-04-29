def compute(s1, s2)
      total = 0
      min_length = s1.length > s2.length ? s2.length : s1.length
      (0...min_length).each do |index|
        total += 1 if s1[index] != s2[index]
      end
      total
    end
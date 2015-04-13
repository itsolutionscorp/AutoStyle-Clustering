def compute(s1, s2)




    diff = 0
    s1.length.times do |i|
      if s1[i] != s2[i] and !s2[i].nil?
        diff += 1
      end
    end
    return diff
  end
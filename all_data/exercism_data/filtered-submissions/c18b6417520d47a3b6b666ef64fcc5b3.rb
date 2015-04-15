def compute(s1, s2)
    counter = 0
    (0..s1.length-1).each do |i|
      counter += 1 if s1[i] != s2[i]
    end
    return counter
  end
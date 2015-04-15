def compute(s1, s2)
    response = 0
    #assume that s1 and s2 have the same length
    (0..s1.length).each do |i|
      if s1[i] != s2[i]
        response += 1
      end
    end
    response
  end
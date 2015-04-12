def compute(s1, s2)
    diff = 0

    len = [s1.length, s2.length].min

    (0..len-1).each do |i|
      if s1[i] != s2[i]
        diff += 1
      end
    end
   diff
  end
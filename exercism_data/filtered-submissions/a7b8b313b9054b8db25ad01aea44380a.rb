def compute(s1, s2)
    result = 0

    size = [s1.length, s2.length].min


    size.times do |i|
      if s1[i] != s2[i]
        result += 1
      end
    end

    result
  end
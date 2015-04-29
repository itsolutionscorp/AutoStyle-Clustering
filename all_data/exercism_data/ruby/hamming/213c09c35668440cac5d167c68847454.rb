class Hamming

  def self.compute(s1, s2)

    i, distance = 0, 0

    if s1.length != s2.length
      if s1.length < s2.length
        s2 = s2[0...(s1.length)]
      else
        s1 = s1[0...(s2.length)]
      end
    end

    puts s1
    puts s2

    while i < s1.length
      if s1[i] != s2[i]
        distance += 1
      end
        i += 1
    end
    distance
  end

end

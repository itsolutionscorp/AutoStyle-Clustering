class Hamming

  def compute(s1,s2)

    dif = 0

    s1.length.times do |i|
      if s1[i].nil?
        return dif
      end

      if s2[i].nil?
        return dif
      end

      if s1[i] != s2[i]
        dif += 1
      end

    end

    return dif

  end
end

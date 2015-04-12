class Hamming

  def compute(s1, s2)
    hd = 0
    for i in 0 .. [s1.length, s2.length].min
      if s1[i] && s2[i]
        hd = hd + 1 unless (s1[i] == s2[i])
      end
    end
    hd
  end
end

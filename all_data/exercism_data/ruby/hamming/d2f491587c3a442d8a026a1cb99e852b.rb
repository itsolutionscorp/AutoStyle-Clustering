class Hamming

  def self.compute(s1, s2)
    hd = 0
    for i in 0 .. [s1.length, s2.length].min-1
      hd = hd + 1 unless (s1[i] == s2[i])
    end
    hd
  end
end

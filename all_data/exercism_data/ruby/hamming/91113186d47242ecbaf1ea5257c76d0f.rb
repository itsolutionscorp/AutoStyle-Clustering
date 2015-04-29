class Hamming

  def self.compute(s1, s2)
    # hd = 0
    # for i in 
    #   hd = hd + 1 unless (s1[i] == s2[i])
    # end
    # hd
    (0 .. [s1.length, s2.length].min-1).count {|i|  s1[i] != s2[i]} 
  end
end

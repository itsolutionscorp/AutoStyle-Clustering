class Hamming

  def self.compute(s1, s2)
    (0 .. [s1.length, s2.length].min-1).count {|_|  s1[_] != s2[_]} 
  end
end

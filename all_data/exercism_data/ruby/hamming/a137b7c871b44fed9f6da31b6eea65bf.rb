class Hamming

  def self.compute(s1, s2)
     s1.chars.zip(s2.chars).count {|r1, r2| r2 && r1 != r2}
  end

end

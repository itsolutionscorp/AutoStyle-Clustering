class Hamming

  def self.compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count do |s1, s2|
      self.substitution?(s1, s2)
    end
  end

  def self.substitution?(s1, s2)
    s1 && s2 && s1 != s2
  end

end

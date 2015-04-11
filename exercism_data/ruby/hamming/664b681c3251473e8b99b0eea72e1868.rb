class Hamming

  def self.compute(original, mutated)
    pair_them(original, mutated).count {|x,y| not_matching(x, y)}
  end

  private
  def self.shorter(s1, s2)
    (s1.length < s2.length) ? s1 : s2
  end

  def self.longer(s1, s2)
    (s1.length < s2.length) ? s2 : s1
  end

  def self.pair_them(s1, s2)
    shorter(s1, s2).chars.zip(longer(s1, s2).chars)
  end

  def self.not_matching(c1, c2)
    c1 != c2
  end

end

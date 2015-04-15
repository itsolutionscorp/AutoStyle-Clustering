class Hamming

  def self.compute(original, mutated)
    pair_them(original, mutated).count {|x,y| not_matching(x, y)}
  end

  private
  def self.shorter_array(s1, s2)
    (s1.length < s2.length) ? s1.chars : s2.chars
  end

  def self.longer_array(s1, s2)
    (s1.length < s2.length) ? s2.chars : s1.chars
  end

  def self.pair_them(s1, s2)
    shorter_array(s1, s2).zip(longer_array(s1, s2))
  end

  def self.not_matching(c1, c2)
    c1 != c2
  end

end

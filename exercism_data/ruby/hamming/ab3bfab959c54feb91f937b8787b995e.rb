class Hamming

  def self.compute(original, mutated)
    pair_them(original, mutated).count {|x,y| not_matching(x, y)}
  end

  private
  def self.pair_them(s1, s2)
    s1.chars.zip(s2.chars)
  end

  def self.not_matching(c1, c2)
    c1 != c2 and c2
  end

end

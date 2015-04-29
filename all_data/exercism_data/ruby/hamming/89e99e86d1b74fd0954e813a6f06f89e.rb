class Hamming

  def self.compute(original, mutated)
    pair_them(original, mutated).count {|x,y| (x != y and y)}
  end

  private
  def self.pair_them(s1, s2)
    s1.to_array.zip(s2.to_array)
  end

  def self.not_matching(c1, c2)
    c1 != c2 and c2
  end

end


class String

  def to_array
    self.split("")
  end

end

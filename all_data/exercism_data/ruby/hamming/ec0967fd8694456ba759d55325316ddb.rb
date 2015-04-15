class Hamming

  def self.compute(original, mutated)
    result = 0
    self.pair_them(original, mutated).each do |x,y|
      if self.not_matching(x,y)
        result += 1
      end
    end
    result
  end

#  private
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

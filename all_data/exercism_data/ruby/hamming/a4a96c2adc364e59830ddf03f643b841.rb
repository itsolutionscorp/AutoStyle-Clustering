class Hamming
  def self.compute(s1, s2)
    new(s1, s2).compute
  end

  def initialize(s1, s2)
    @s1 = s1
    @s2 = s2
  end

  def compute
    char_pairs.count { |char1, char2| char1 != char2 }
  end

  private

  def char_pairs
    pairs = @s1.chars.zip(@s2.chars)
    pairs.keep_if { |pair| pair.first && pair.last }
  end
end

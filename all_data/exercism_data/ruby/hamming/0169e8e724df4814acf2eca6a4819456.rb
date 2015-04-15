class Hamming
  def self.compute(s1, s2)
    new(s1, s2).compute
  end

  def initialize(s1, s2)
    @s1 = s1
    @s2 = s2
  end

  def compute
    char_pairs.count { |pair| exist_and_no_match?(pair) }
  end

  private

  def char_pairs
    @s1.chars.zip(@s2.chars)
  end

  def exist_and_no_match?(pair)
    (pair.first && pair.last) && pair.first != pair.last
  end
end

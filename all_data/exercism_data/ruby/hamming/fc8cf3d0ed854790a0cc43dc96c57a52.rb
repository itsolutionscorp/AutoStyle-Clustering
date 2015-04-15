class Hamming
  def self.compute(s1, s2)
    new(s1, s2).compute
  end

  def initialize(s1, s2)
    @s1 = s1
    @s2 = s2
  end

  def compute
    char_pairs.count { |char1, char2| exist_and_no_match?(char1, char2) }
  end

  private

  def char_pairs
    @s1.chars.zip(@s2.chars)
  end

  def exist_and_no_match?(char1, char2)
    (char1 && char2) && char1 != char2
  end
end

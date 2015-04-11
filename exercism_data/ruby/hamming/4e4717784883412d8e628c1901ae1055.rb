class Hamming
  def self.compute(string_a, string_b)
    new(string_a, string_b).distance
  end

  def initialize(string_a, string_b)
    @string_a, @string_b = string_a, string_b
  end

  def pairs
    @string_a.chars.zip(@string_b.chars).select { |left, right| left && right }
  end

  def distance
    pairs.count { |left, right| left != right }
  end
end

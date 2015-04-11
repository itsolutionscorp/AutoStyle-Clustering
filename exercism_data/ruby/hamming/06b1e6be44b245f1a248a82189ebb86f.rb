class Hamming
  def self.compute(string, other_string)
    pairs(string, other_string).count { |a, b| a != b }
  end

  def self.pairs(left, right)
    left.chars.zip(right.chars)
  end
end

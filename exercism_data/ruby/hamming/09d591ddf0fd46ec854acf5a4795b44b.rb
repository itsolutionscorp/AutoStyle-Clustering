class Hamming
  def self.compute(left, right)
    left.chars.zip(right.chars).count { |a, b| a != b }
  end
end
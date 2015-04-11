class Hamming
  def self.compute(original, comparison)
    original.chars.zip(comparison.chars).count { |a, b| a != b and a and b }
  end
end

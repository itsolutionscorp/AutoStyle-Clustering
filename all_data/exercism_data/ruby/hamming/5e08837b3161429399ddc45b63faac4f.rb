class Hamming
  def self.compute(original, comparison)
    original.chars.zip(comparison.chars).select { |a, b| a != b and a and b }.length
  end
end

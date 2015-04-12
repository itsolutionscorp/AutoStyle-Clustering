class Hamming
  def compute(original, comparison)
    original.chars.zip(comparison.chars).select { a != b and a and b }.length
  end
end

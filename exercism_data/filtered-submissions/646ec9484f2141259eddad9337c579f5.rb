def compute(original, comparison)
    original.chars.zip(comparison.chars).count { |a, b| a != b and a and b }
  end
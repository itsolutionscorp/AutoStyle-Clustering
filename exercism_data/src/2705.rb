module Hamming
  def compute(strand_a, strand_b)
    pairs = strand_a.chars.zip(strand_b.chars)
    pairs.reject(&:one?).count { |a, b| a != b }
  end
end

class Hamming
  def self.compute(strand_a, strand_b)
    positional_pairs(strand_a, strand_b).count { |a, b| a != b }
  end

  private

  def self.positional_pairs(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).take(strand_b.size)
  end
end

class Hamming
  def self.compute(strand_a, strand_b)
    new(strand_a, strand_b).difference
  end

  attr_reader :strand_a, :strand_b

  def initialize(strand_a, strand_b)
    @strand_a = strand_a.chars
    @strand_b = strand_b.chars
  end

  def positional_pairs
    strand_a.zip(strand_b)
  end

  def difference
    positional_pairs.count { |a, b| a != b }
  end
end

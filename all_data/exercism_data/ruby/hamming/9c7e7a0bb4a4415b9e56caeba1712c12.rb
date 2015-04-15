class Hamming
  def self.compute(strand, other)
    Hamming.new(strand, other).distance
  end

  def initialize(strand, other)
    @strand = strand
    @other = other
  end

  def distance
    positional_pairs.count { |a, b| a != b }
  end

  private

  attr_reader :strand, :other

  def positional_pairs
    strand.chars.zip(other.chars).take(other.size)
  end
end

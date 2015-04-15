class Hamming

  def initialize(strand, other)
    @strand = strand
    @other  = other
  end

  def self.compute(strand, other)
    Hamming.new(strand, other).distance
  end

  def distance
    pairs.count { |(a, b)| a != b }
  end

  private

  def pairs
    strand.chars.zip(other.chars).take(length)
  end

  def length
    [strand.length, other.length].min
  end

  attr_reader :strand, :other
end

class Hamming

  def initialize(strand, other)
    @strand = DNA.new(strand)
    @other  = DNA.new(other)
  end

  def self.compute(strand, other)
    Hamming.new(strand, other).distance
  end

  def distance
    pairs.count { |(a, b)| a != b }
  end

  private

  def pairs
    [strand, other].map(&:nucleotides).reduce(:zip).take(length)
  end

  def length
    [strand, other].map(&:length).min
  end

  attr_reader :strand, :other
end

class DNA

  def initialize(strand)
    @strand = strand
  end

  def nucleotides
    strand.chars
  end

  def length
    strand.length
  end

  attr_reader :strand

end

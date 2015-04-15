class Hamming

  def self.compute(strand_a, strand_b)
    self.new(strand_a).distance_to(strand_b)
  end

  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def distance_to(strand)
    pairs = bases.zip(strand.chars)
    pairs.take(strand.length).count do |a, b|
      a != b
    end
  end

private

  def bases
    strand.chars
  end
end

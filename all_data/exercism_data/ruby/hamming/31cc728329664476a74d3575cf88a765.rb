class Hamming < Struct.new(:strand_a, :strand_b)

  def self.compute(strand_a, strand_b)
    new(strand_a.chars, strand_b.chars).distance
  end

  def initialize(strand_a, strand_b)
    @strand_a, @strand_b = strand_a.chars, strand_b.chars
  end

  def distance
    unmatching_pairs.length
  end

  private

  def pairs
    @strand_a.zip @strand_b
  end

  def unmatching_pairs
    pairs.select do |a, b|
      a != b
    end
  end

end

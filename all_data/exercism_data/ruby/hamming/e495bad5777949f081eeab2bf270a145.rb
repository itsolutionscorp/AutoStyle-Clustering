class Hamming
  def self.compute strand_a, strand_b
    StrandPair.new(strand_a, strand_b).nucleotide_pairs.select(&:mutated?).count
  end
end

class StrandPair
  attr_reader :strand_a, :strand_b, :length

  def initialize strand_a, strand_b
    @strand_a = strand_a.chars
    @strand_b = strand_b.chars
    @length = [@strand_a.length, @strand_b.length].min
  end

  def nucleotide_pairs
    (0..length-1).map { |i| NucleotidePair.new strand_a[i], strand_b[i] }
  end

  class NucleotidePair < Struct.new :a, :b
    def mutated?
      a != b
    end
  end
end

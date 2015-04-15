class Hamming
  def self.compute(dna_strand_a, dna_strand_b)
    return 0 if dna_strand_a == dna_strand_b
    strand_a = DNAStrand.new(dna_strand_a)
    strand_b = DNAStrand.new(dna_strand_b)
    strand_a.hamming_distance(strand_b)
  end
end

class DNAStrand
  attr_accessor :strand_representation
  def initialize(strand_representation)
    self.strand_representation = strand_representation
  end

  def nucleotides
    strand_representation.split('')
  end

  def length
    strand_representation.length
  end

  def hamming_distance(other)
    length_for_comparison = [length, other.length].min
    distance = 0
    comparable_nucleotides(length_for_comparison).each_with_index do |nucleotide, index|
      distance += 1 if nucleotide != other.nucleotides[index]
    end
    distance
  end

  private
  def comparable_nucleotides(length_for_comparison)
    nucleotides.slice(0, length_for_comparison)
  end
end

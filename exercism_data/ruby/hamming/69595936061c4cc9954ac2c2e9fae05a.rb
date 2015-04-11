class Hamming
  def self.compute(reference_strand, other_strand)
    dna = DNA.new(reference_strand)
    dna.hamming_distance(other_strand)
  end
end

class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    common_length = [strand.size, other_strand.size].min
    common_length.times.count { |index| strand[index] != other_strand[index] }
  end

  private

  attr_reader :strand
end

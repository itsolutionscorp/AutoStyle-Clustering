class Hamming
  def self.compute(sequence1, sequence2)
    dna_strand1 = DnaStrand.new(sequence1)
    dna_strand2 = DnaStrand.new(sequence2)

    dna_strand1.point_mutations(dna_strand2).size
  end
end

class DnaStrand
  attr_reader :sequence

  def initialize(sequence_string)
    @sequence = sequence_string.chars
  end

  def point_mutations(other_strand)
    non_matching_pairs((nucleotide_pairs(other_strand)))
  end

  private

  def non_matching_pairs(pairs)
    pairs.select {|pair| pair.compact.uniq == pair }
  end

  def nucleotide_pairs(other_strand)
    sequence.zip(other_strand.sequence)
  end
end

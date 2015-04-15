class Hamming
  def self.compute(sequence1, sequence2)
    dna_strand1 = DnaStrand.new(sequence1)
    dna_strand2 = DnaStrand.new(sequence2)

    dna_strand1.unique_pairs(dna_strand2).size
  end
end

class DnaStrand
  attr_reader :sequence

  def initialize(sequence_string)
    @sequence = sequence_string.chars
  end

  def unique_pairs(other_strand)
    unique_filter(sequence_pairs(other_strand))
  end

  private

  def sequence_pairs(other_strand)
    sequence.zip(other_strand.sequence)
  end

  def unique_filter(pairs)
    pairs.select {|pair| pair.compact.uniq == pair }
  end
end

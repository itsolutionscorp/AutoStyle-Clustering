class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(alt_sequence)
    different_nucleotide_placements(alt_sequence).count
  end

  def different_nucleotide_placements(alt_sequence)
    diff = []
    sequence.chars.each_with_index do |nucleotide, index|
      diff << nucleotide if (nucleotide != alt_sequence[index] && alt_sequence[index])
    end
    diff
  end
end

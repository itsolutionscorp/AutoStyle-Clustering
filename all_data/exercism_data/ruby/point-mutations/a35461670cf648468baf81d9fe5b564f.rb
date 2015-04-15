class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(mutation)
    count_different_nucleotide_placements(mutation)
  end

  def count_different_nucleotide_placements(mutation)
    nucleotide_pairs(mutation).count do |pair|
      !equal(pair[0],pair[1]) && !has_nil(pair[0], pair[1])
    end
  end

  def nucleotide_pairs(mutation)
    sequence.chars.zip mutation.chars
  end

  def has_nil(*nucleotides)
    nucleotides.any? {|nucleotide| nucleotide.nil?}
  end

  def equal(nucleotide1, nucleotide2)
    nucleotide1 == nucleotide2
  end
end

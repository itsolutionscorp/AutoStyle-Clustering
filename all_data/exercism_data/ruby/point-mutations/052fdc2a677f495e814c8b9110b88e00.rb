class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other_sequence)
    pairs_with_mutations(other_sequence).count
  end

  private

  attr_reader :sequence

  def pairs_with_mutations(other_sequence)
    nucleotide_pairs(other_sequence).select do |n1, n2|
      mutation?(n1, n2)
    end
  end

  def nucleotide_pairs(other_sequence)
    sequence.chars.zip(other_sequence.chars).reject do |pair|
      pair.include?(nil)
    end
  end

  def mutation?(n1, n2)
    n1 != n2
  end

end

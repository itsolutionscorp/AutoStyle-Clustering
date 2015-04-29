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
    sequence.chars.zip(other_sequence.chars).select do |n1, n2|
      mutation?(n1, n2)
    end
  end

  def mutation?(n1, n2)
    !n2.nil? && n1 != n2
  end

end

class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other_sequence)
    sequence_pairs(other_sequence).count { |pair| pair.first != pair.last }
  end

  private

  def sequence_pairs(other_sequence)
    length = target_length(other_sequence)
    @sequence[0...length].chars.zip(other_sequence[0...length].chars)
  end

  def target_length(other_sequence)
    [other_sequence.length, @sequence.length].min
  end

end

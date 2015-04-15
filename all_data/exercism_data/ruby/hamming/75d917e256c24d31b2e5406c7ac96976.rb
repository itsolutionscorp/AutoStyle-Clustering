class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other_sequence)
    @sequence.chars.zip(other_sequence.chars)
                   .count {|a,b| a and b and a != b }
  end
end

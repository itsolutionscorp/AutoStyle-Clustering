class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other_sequence)
    overlap_size = [sequence.size, other_sequence.size].min

    my_segment    = sequence[0,overlap_size]
    other_segment = other_sequence[0,overlap_size]

    paired_bases = my_segment.chars.zip(other_segment.chars)
    paired_bases.count {|(mine,other)| mine != other }
  end
end

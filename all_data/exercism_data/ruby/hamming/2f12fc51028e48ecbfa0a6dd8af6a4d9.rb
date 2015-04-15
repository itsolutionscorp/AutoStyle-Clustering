class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other)
    length = [sequence, other].map(&:length).min
    (0...length).count {|i| sequence[i] != other[i] }
  end

end

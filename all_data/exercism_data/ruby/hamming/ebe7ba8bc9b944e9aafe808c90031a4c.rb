class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(sequence)
    length = [@sequence, sequence].map(&:length).min
    (0...length).count {|i| @sequence[i] != sequence[i] }
  end

end

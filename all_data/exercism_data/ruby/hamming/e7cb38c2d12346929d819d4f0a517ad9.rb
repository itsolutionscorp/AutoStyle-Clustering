class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(sequence)
    left, right = @sequence.chars, sequence.chars
    length = [left.length, right.length].min - 1
    (0..length).count {|i| left[i] != right[i] }
  end
end

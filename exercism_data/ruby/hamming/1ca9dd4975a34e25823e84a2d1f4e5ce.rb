class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(sequence)
    left, right = @sequence.chars, sequence.chars
    length = shortest_string_length(left, right)
    (0..length).count {|i| left[i] != right[i] }
  end

  private
  def shortest_string_length(left, right)
    [left.length, right.length].min - 1
  end

end

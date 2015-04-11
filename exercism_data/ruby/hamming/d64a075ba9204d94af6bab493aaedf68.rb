class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other_sequence)
    distance = 0
    length = shortest_length sequence, other_sequence

    (0...length).each do |index|
      distance += 1 if sequence[index] != other_sequence[index]
    end

    distance
  end

  private

  def shortest_length(sequence, other_sequence)
    [sequence.length, other_sequence.length].min
  end
end

class DNA
  attr_reader :sequence; private :sequence

  def initialize sequence
    @sequence = sequence
  end

  def hamming_distance ancestor
    distance  = 0
    length = shortest_length ancestor, sequence
    length.times { |i| distance += 1 if ancestor[i] != sequence[i] }
    distance
  end

  private

  def shortest_length sequence1, sequence2
    [sequence1.length, sequence2.length].min
  end
end

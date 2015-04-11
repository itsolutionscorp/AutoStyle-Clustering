class DNA
  def initialize(sequence)
    @sequence = sequence
  end
  
  def hamming_distance(strand)
    distance = 0
    [@sequence.size, strand.size].min.times do |char_pos|
      distance += 1 unless @sequence[char_pos] == strand[char_pos]
    end
    distance
  end
end

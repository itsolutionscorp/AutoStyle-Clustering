class DNA

  def initialize sequence
    @sequence = sequence
  end

  def hamming_distance sequence
    distance = 0
    @sequence.chars.each_with_index { |n, i| distance += 1 if n != sequence[i] && sequence[i] }
    distance
  end
end

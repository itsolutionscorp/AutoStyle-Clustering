class DNA

  def initialize sequence
    @sequence = sequence
  end

  def hamming_distance sequence
    distance = 0
    @sequence.chars.each_with_index { |n, i| distance += 1 if !matching_nucleotides? i, sequence }
    distance
  end

  private

  def matching_nucleotides? index, sequence
    (@sequence[index] == sequence[index] || !sequence[index])
  end
end

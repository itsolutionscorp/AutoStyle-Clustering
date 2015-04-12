class Hamming
  def compute(strand, other_strand)
    strand.each_char.with_index.reduce(0) do |distance, char_with_index|
      char, i = *char_with_index
      distance += 1 unless char == other_strand[i]
      distance
    end
  end
end

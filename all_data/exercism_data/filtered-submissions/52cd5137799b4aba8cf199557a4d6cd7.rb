def compute strand, other_strand
    hamming_diff = 0
    char_index = 0
    while (char_index < strand.length) do
      mutation = strand[char_index] != other_strand[char_index]
      hamming_diff += 1 if mutation
      char_index += 1
    end
    hamming_diff
  end
def compute(first_sequence,second_sequence)
    hamming_distance = 0
    index = 0
    strand_length = first_sequence.length

    while index < strand_length
      hamming_distance += 1 if first_sequence[index] != second_sequence[index]
      index += 1
    end

    hamming_distance
  end
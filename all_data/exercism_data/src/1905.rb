def compute(first_sequence, second_sequence)
    first_sequence.chars.select.with_index.count{ |base, index| base != second_sequence.chars[index] }
  end
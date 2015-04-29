def compute(first_sequence, second_sequence)
    (first_sequence.split(//).map.with_index.to_a - second_sequence.split(//).map.with_index.to_a).count
  end
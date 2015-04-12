class Hamming

  def compute(first_sequence, second_sequence)
    (first_sequence.chars.map.with_index.to_a - second_sequence.chars.map.with_index.to_a).count
  end

end

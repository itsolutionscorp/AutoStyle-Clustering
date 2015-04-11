class Hamming
  def self.compute(first_sequence, second_sequence)
    unless first_sequence.length != second_sequence.length
      first_sequence_to_array = first_sequence.chars
      second_sequence_to_array = second_sequence.chars
      (first_sequence_to_array.select.with_index{ |base, index| base != second_sequence_to_array[index] }).length
    end
  end
end

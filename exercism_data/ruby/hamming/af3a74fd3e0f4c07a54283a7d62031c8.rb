class Hamming
  def self.compute(first_sequence, second_sequence)
    unless first_sequence.length != second_sequence.length
      (first_sequence.chars.select.with_index{ |base, index| base != second_sequence.chars[index] }).length
    end
  end
end

class Hamming
  def self.compute(first_sequence, second_sequence)
    first_sequence.chars.each.with_index.count{ |base, index| base != second_sequence.chars[index] }
  end
end

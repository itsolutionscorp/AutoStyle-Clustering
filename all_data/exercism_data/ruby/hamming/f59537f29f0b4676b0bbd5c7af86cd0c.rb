class Hamming
  def self.compute(first_sequence, second_sequence)
    first_sequence.chars.zip(second_sequence.chars).count do |first_char, second_char|
      first_char != second_char
    end
  end
end

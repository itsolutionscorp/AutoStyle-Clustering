class Hamming
  def self.compute(first_sequence, second_sequence)
    distance = 0
    array_from(first_sequence).pop(letter_count_of(second_sequence)).each_index do |i|
      distance += 1 if first_sequence.chars[i] != second_sequence.chars[i]
    end
  distance
  end

  def self.letter_count_of sequence
    sequence.chars.count
  end

  def self.array_from sequence
    sequence.chars
  end
end
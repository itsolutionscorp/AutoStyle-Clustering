class Hamming
  def self.compute (sequence, other_sequence)
    differences_in_sequences = 0

    sequence.each_char.with_index do |value, index|
      unless value === other_sequence[index]
        differences_in_sequences += 1
      end
    end

    differences_in_sequences
  end
end

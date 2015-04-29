class DNA
  def initialize(reference)
    @reference = reference
  end

  def hamming_distance(sequence)
    accumulator = 0
    sequence.chars.each_with_index do |val, index|
      break unless @reference[index]
      accumulator += 1 unless val == @reference[index]
    end
    accumulator
  end
end

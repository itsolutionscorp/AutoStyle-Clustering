class DNA
  def initialize(base_sequence)
    @base_sequence = base_sequence
  end

  def hamming_distance(other_sequence)
    other_sequence = other_sequence.chars

    # Ignore empty input.
    return 0 if other_sequence.size == 0

    # Normalize length.
    size = [@base_sequence.size, other_sequence.size].min
    base_sequence = @base_sequence[0, size]
    other_sequence = other_sequence[0, size]

    # Perform the comparison
    (0...size).inject(0) do |sum, i|
      base_sequence[i] != other_sequence[i] ? sum + 1 : sum
    end
  end
end

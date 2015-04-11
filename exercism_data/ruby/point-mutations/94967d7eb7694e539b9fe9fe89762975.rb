class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(new_sequence)
    result = 0
    minimum_length(@sequence, new_sequence).times do |i|
      result += 1 unless nucleotide_matches?(i, new_sequence)
    end
    result
  end

  private

  def minimum_length(s1, s2)
    [s1.length, s2.length].min
  end

  def nucleotide_matches?(i, new_sequence)
    @sequence[i] == new_sequence[i]
  end
end

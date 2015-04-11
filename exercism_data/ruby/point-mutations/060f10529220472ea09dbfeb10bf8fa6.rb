class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(new_sequence)
    result = 0
    minimum_length(@sequence, new_sequence).times do |i|
      result += 1 unless nucleotide_matches?(i, @sequence, new_sequence)
    end
    result
  end

  private

  def minimum_length(s1, s2)
    [s1.length, s2.length].min
  end

  def nucleotide_matches?(i, sequence1, sequence2)
    sequence1[i] == sequence2[i]
  end
end

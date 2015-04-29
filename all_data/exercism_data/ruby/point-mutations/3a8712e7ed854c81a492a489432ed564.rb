class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(new_sequence)
    result = 0
    minimum_length(@sequence, new_sequence).times do |i|
      result += 1 unless @sequence[i] == new_sequence[i]
    end
    result
  end

  private

  def minimum_length(string1, string2)
    ([string1, string2].min_by { |s| s.length }).length
  end
end

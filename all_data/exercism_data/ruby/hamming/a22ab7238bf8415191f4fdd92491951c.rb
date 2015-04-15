class DNA
  def initialize string
    @string = string
  end

  def hamming_distance other_string
    @string.
      chars[0, other_string.length].
      zip(other_string.chars).
      reduce(0) do |hamming_distance, n_pair|
        n_pair.first == n_pair.last ? hamming_distance : hamming_distance += 1
      end
  end
end

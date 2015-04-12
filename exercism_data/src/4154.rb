class Hamming
  def compute(seq_a, seq_b)
    raise ArgumentError unless seq_a.length == seq_b.length
    char_pairs = seq_a.chars.zip(seq_b.chars)
    char_pairs.inject(0) do |hamming_distance, char_pair|
      hamming_distance + char_pair.uniq.length - 1
    end
  end
end

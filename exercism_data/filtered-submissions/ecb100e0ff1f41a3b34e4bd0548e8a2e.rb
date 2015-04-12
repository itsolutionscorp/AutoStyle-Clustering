class Hamming
  def compute(seq_a, seq_b)
    seq_a.chars.zip(seq_b.chars).map do |pair|
      pair.first == pair.last ? 0 : 1
    end.reduce(:+)
  end
end

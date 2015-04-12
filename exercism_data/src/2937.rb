class Hamming
  def compute(strand_a, strand_b)
    [strand_a, strand_b].map(&:chars).transpose.reject{|(a,b)| a == b }.count
  end
end

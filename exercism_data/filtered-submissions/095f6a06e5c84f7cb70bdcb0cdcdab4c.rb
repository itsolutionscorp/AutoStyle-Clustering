class Hamming
  def compute(strand_one, strand_two)
    pairs = strand_one.chars.zip(strand_two.chars)
    pairs = pairs[0..strand_two.chars.length - 1]
    pairs.count { |pair| pair[0] != pair[1] }
  end
end

class Hamming
  def compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count {|a, b| a != b}
  end
end

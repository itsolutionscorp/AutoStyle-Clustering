class Hamming
  def compute(strand_1, strand_2)
    strand_1.chars
            .zip(strand_2.chars)
            .count{ |a, b| a != b }
  end
end

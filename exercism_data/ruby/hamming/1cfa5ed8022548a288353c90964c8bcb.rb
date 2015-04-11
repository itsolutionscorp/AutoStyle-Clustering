class Hamming
  def self.compute(strand_1, strand_2)
    strand_1.chars.zip(strand_2.chars).reduce(0) { |count, pair| count + (pair[0] != pair[1] ? 1 : 0) }
  end
end

# class to compute Hamming difference in 2 DNA strands
class Hamming
  def self.compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).reduce(0) { |matches, pair| pair[0] != pair[1] ? matches += 1 : matches }
  end
end

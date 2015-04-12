# class to compute Hamming difference in 2 DNA strands
class Hamming
  def compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).count { |pair| pair[0] != pair[1] }
  end
end
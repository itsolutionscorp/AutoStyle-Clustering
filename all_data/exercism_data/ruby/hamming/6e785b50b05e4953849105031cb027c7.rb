# class to compute Hamming difference in 2 DNA strands
class Hamming
  def self.compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).count { |nuc_a, nuc_b| nuc_a != nuc_b }
  end
end

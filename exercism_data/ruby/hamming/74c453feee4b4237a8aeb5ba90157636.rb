class Hamming
  def self.compute(strand_a, strand_b)
    nucleotide_pairs = strand_a.chars.zip(strand_b.chars)
    nucleotide_pairs.count {|a,b| a != b}
  end
end

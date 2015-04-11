class Hamming
  def self.compute(strand_a, strand_b)
    nucleotide_pairs = strand_a.chars.zip(strand_b.chars)
    nucleotide_pairs.count { |x| !x.include?(nil) && x.first != x.last }
  end
end

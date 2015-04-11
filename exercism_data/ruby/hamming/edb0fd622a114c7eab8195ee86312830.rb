class Hamming
  def self.compute(strand_a, strand_b)
    nucleotide_pairs = strand_a.chars.zip(strand_b.chars)
    nucleotide_pairs.inject(0){ |acc, pair| acc += nucleic_distance(pair) }
  end

  private
  def self.nucleic_distance(nucleotide_pair)
    a, b = nucleotide_pair
    a == b || nucleotide_pair.include?(nil) ? 0 : 1
  end
end

class Hamming
  def compute(dna_strand_a, dna_strand_b)
    dna_strand_a.chars.zip(dna_strand_b.chars).count { |nuc_a, nuc_b| nuc_a != nuc_b }
  end
end

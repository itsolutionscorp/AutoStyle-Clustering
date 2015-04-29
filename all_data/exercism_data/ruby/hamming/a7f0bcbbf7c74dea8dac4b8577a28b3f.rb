class Hamming
  def self.compute first_dna_strand, second_dna_strand
    first_dna_bases  = first_dna_strand.chars.to_a
    second_dna_bases = second_dna_strand.chars.to_a

    dna_bases_comparisons = strand_comparisons_of(first_dna_bases, second_dna_bases)
    dna_bases_comparisons.count { |comparison| comparison[0] != comparison[1] }
  end

  def self.strand_comparisons_of first_dna_bases, second_dna_bases
    valid_comparisons_size = [first_dna_bases.size, second_dna_bases.size].min
    all_pairs = first_dna_bases.zip(second_dna_bases)
    all_pairs[0...valid_comparisons_size]
  end
end

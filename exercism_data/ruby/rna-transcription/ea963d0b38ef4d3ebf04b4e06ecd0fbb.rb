class Complement

  NUCLEOTIDES_COMPLEMENTS = { G: 'C', C: 'G', T: 'A', A: 'U'}

  def self.of_dna(dna_strand)
    dna_strand.chars.map { |nucleotide| complement_of(nucleotide.to_sym) }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map { |nucleotide| complement_of_rna(nucleotide) }.join
  end

  private
  def self.complement_of(nucleotide)
    NUCLEOTIDES_COMPLEMENTS[nucleotide]
  end

  def self.complement_of_rna(nucleotide)
    NUCLEOTIDES_COMPLEMENTS.key(nucleotide).to_s
  end

end

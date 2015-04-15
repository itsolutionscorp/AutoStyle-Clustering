class Complement

  NUCLEOTIDES_COMPLEMENTS = { G: 'C', C: 'G', T: 'A', A: 'U'}

  def self.of_dna(dna_strand)
    complement = ""
    dna_strand.each_char { |nucleotide| complement << complement_of(nucleotide.to_sym) }
    complement
  end

  def self.of_rna(rna_strand)
    complement = ""
    rna_strand.each_char { |nucleotide| complement << complement_of_rna(nucleotide)}
    complement
  end

  private
  def self.complement_of(nucleotide)
    NUCLEOTIDES_COMPLEMENTS[nucleotide]
  end

  def self.complement_of_rna(nucleotide)
    NUCLEOTIDES_COMPLEMENTS.key(nucleotide).to_s
  end

end

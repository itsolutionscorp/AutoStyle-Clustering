class Complement
  NUCLEOTIDES = ['GCTA', 'CGAU']

  def self.of_dna(dna_strand)
    dna_strand.tr(*NUCLEOTIDES)
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(*NUCLEOTIDES.reverse)
  end
end

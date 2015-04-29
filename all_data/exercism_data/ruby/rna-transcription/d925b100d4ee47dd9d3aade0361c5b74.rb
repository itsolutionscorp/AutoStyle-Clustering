module Complement
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  def self.of_dna(dna)
    dna.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(rna)
    rna.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end
end

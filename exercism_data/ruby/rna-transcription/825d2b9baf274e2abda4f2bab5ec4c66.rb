class Complement
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  def self.of_dna(nucleotides)
    nucleotides.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(nucleotides)
    nucleotides.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end
end

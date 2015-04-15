class Complement
  DNA_NUCLEOTIDES = 'CGTA'
  RNA_NUCLEOTIDES = 'GCAU'

  def self.of_dna(strand)
    strand.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(strand)
    strand.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end
end

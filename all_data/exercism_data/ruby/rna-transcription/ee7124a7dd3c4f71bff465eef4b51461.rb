class Complement
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'
  def self.of_dna(strand)
    strand.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end
  def self.of_rna(strand)
    strand.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)    
  end
end

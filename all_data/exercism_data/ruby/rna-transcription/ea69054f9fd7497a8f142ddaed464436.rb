class Complement
  DNA_NUCLEOTIDS = 'GCTA'
  RNA_NUCLEOTIDS = 'CGAU'
  
  def self.of_dna(dna_string)
    dna_string.tr(DNA_NUCLEOTIDS, RNA_NUCLEOTIDS)
  end
  
  def self.of_rna(rna_string)
    rna_string.tr(RNA_NUCLEOTIDS, DNA_NUCLEOTIDS)
  end
end

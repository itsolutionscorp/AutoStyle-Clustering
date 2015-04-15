class Complement
  DNA_NUCLEOTIDES = "GCTA"
  RNA_NUCLEOTIDES = "CGAU"
  
  def self.of_dna strand
    transform strand, [DNA_NUCLEOTIDES,RNA_NUCLEOTIDES]
  end
  
  def self.of_rna strand
    transform strand, [RNA_NUCLEOTIDES,DNA_NUCLEOTIDES]
  end
  
  def self.transform string, complements
    string.tr *complements
  end
end

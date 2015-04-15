class Complement

  DNA_NUCLEOTIDE = "CGTA"
  RNA_NUCLEOTIDE = "GCAU"  

  def self.of_dna(strand)    
    strand.tr(DNA_NUCLEOTIDE, RNA_NUCLEOTIDE)
  end

  def self.of_rna(strand)    
    strand.tr(RNA_NUCLEOTIDE, DNA_NUCLEOTIDE)
  end
end

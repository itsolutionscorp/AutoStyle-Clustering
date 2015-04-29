class Complement
  DNA_NUCLEOTIDE = "GCTA"
  RNA_NUCLEOTIDE = "CGAU"

  def self.of_dna dna
    dna.tr DNA_NUCLEOTIDE, RNA_NUCLEOTIDE
  end

  def self.of_rna rna
    rna.tr RNA_NUCLEOTIDE, DNA_NUCLEOTIDE
  end

end

class Complement
  DNA_NUCLEOTIDES = /[GCTA]/
  RNA_NUCLEOTIDES = /[GCUA]/
  DNA_COMPLEMENTS = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U' }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert
  def self.of_dna(strand)
    strand.gsub(DNA_NUCLEOTIDES,DNA_COMPLEMENTS)
  end 
  def self.of_rna(strand)
    strand.gsub(RNA_NUCLEOTIDES,RNA_COMPLEMENTS)
  end 
end

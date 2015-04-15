class Complement
  RNA_NUCLEOTIDES = /[GCAU]/
  DNA_NUCLEOTIDES = /[GCTA]/


  def self.of_dna(dna_strand)
    dna_strand.gsub(DNA_NUCLEOTIDES, 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U')
  end

  def self.of_rna(rna_strand)
    rna_strand.gsub(RNA_NUCLEOTIDES, 'G'=>'C', 'C'=>'G', 'A'=>'T', 'U'=>'A')
  end

end

class Complement

  DNA_TO_RNA_COMPLEMENTS = {'G' => 'C', 
  	                        'C' => 'G', 
  	                        'T' => 'A', 
  	                        'A' => 'U'}

  DNA_NUCLEOTIDES = 'GCTA'

  RNA_NUCLEOTIDES = 'CGAU'
  
  def self.of_dna(dna)
    raise ArgumentError, 'Argument has not DNA nucleotides' unless dna.match(/[#{DNA_NUCLEOTIDES}]/)
    dna.gsub(/[#{DNA_NUCLEOTIDES}]/, DNA_TO_RNA_COMPLEMENTS)
  end

  def self.of_rna(rna)
    raise ArgumentError, 'Argument has not RNA nucleotides' unless rna.match(/[#{RNA_NUCLEOTIDES}]/)
    rna.gsub(/[#{RNA_NUCLEOTIDES}]/, DNA_TO_RNA_COMPLEMENTS.invert)
  end

end

class Complement

  DNA_TO_RNA_COMPLEMENTS = {'G' => 'C', 
  	                        'C' => 'G', 
  	                        'T' => 'A', 
  	                        'A' => 'U'}
  
  def self.of_dna(dna)
    raise ArgumentError, 'Argument has a not the right nucleotides that are found in DNA' if dna.match(/[^GCTA]/)
    dna.gsub(/[GCTA]/, DNA_TO_RNA_COMPLEMENTS)
  end

  def self.of_rna(rna)
    raise ArgumentError, 'Argument has a not the right nucleotides that are found in RNA' if rna.match(/[^CGAU]/)
    rna.gsub(/[CGAU]/, DNA_TO_RNA_COMPLEMENTS.invert)
  end

end

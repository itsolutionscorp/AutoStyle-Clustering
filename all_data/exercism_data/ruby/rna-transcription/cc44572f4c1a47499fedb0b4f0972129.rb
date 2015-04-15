class Complement
  DNA_COMPLEMENTS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert
    
  def self.of_dna(dna)
    transcribe(dna, DNA_COMPLEMENTS) 
  end
  
  def self.of_rna(rna)
    transcribe(rna, RNA_COMPLEMENTS)
  end
  
  private
  
  def self.transcribe(strand, complements)
    strand.chars.map{ |char| char = complements[char] }.join   
  end
end

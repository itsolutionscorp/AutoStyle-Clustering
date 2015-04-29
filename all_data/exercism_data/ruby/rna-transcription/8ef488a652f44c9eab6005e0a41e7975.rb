class Complement
  COMPLEMENTS = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
  
  def self.of_dna(strand)
    strand.gsub( /[GCTA]/, COMPLEMENTS)
  end
  
  def self.of_rna(strand)
    strand.gsub( /[CGAU]/, COMPLEMENTS.invert)
  end
end

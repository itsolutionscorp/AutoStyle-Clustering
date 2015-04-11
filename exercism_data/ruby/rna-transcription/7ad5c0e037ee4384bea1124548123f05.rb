class Complement
  
  DNA_SUBSTITUTION  = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
  RNA_SUBSTITUTION  = DNA_SUBSTITUTION.invert


  def self.of_dna(strand)
    strand.gsub(/[CGTA]/, DNA_SUBSTITUTION)
  end

  def self.of_rna(strand)
    strand.gsub(/[CGAU]/, RNA_SUBSTITUTION)
  end
end

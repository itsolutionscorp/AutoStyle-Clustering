class Complement
  RULE = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  
  def self.of_dna dna
    dna.gsub(/[GCTA]/, RULE)
  end
  
  def self.of_rna rna
    rna.gsub(/[CGAU]/, RULE.invert)
  end
end

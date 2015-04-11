class Complement
  RNA = { 'A' => 'U', 'C' => 'G', 'T' => 'A', 'G' => 'C'}
  
  def self.of_dna(dna)
    dna.gsub(/[ACGT]/, RNA) 
  end

  def self.of_rna(rna)
    rna.gsub(/[UGAC]/, RNA.invert) 
  end
end

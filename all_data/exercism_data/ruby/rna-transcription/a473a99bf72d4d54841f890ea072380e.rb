class Complement
  RNA = { 'A' => 'U', 'C' => 'G', 'T' => 'A', 'G' => 'C'}
  
  def self.of_dna(dna)
    strand.gsub(/[ACGT]/, RNA) 
  end

  def self.of_rna(rna)
    strand.gsub(/[UGAC]/, RNA.invert) 
  end
end

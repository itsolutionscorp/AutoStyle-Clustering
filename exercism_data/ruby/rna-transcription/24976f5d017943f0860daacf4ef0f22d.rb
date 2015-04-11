class Complement

  def self.of_dna(dna)
    dna.gsub(/[ACGT]/, 'A' => 'U', 'T' => 'A', 'C' => 'G', 'G' => 'C') 
  end

  def self.of_rna(rna)
    rna.gsub(/[ACGU]/, 'U' => 'A', 'A' => 'T', 'C' => 'G', 'G' => 'C') 
  end
end

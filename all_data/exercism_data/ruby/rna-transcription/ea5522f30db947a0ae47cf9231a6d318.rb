class Complement

  def self.of_dna(dna)
    dna.gsub(/[CGTA]/, 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(rna)
    rna.gsub(/[CGUA]/, 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T')
  end

end

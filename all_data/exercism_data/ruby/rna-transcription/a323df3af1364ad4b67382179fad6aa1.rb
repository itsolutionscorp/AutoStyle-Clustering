class Complement

  def self.of_dna(dna)
    dna.gsub(/[CGTA]/, 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(rna)
    rna.gsub(/[GCAU]/, 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A')
  end
end

class Complement
  def self.of_dna(dna)
    dna.upcase.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end
  def self.of_rna(rna)
    rna.upcase.gsub(/[CGAU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
  end
end

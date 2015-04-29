class Complement
  def self.of_dna(dna)
    dna.downcase.gsub(/[gcta]/, 'g' => 'C', 'c' => 'G', 't' => 'A', 'a' => 'U').upcase
  end
  def self.of_rna(rna)
    rna.downcase.gsub(/[cgau]/, 'c' => 'G', 'g' => 'C', 'a' => 'T', 'u' => 'A').upcase
  end
end

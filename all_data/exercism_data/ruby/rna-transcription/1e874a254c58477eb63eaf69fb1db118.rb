class Complement

  def self.of_dna(rna)
    rna.upcase.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(dna)
    dna.upcase.gsub(/[GCUA]/, 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A')
  end

end

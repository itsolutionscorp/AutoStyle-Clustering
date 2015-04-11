class Complement
  def self.of_dna(dna)
    dna_complements = { "G" => "C",
                        "C" => "G",
                        "A" => "U",
                        "T" => "A" }
    
    self.complement(dna, dna_complements)
  end

  def self.of_rna(rna)
    rna_complements = { "G" => "C",
                        "C" => "G",
                        "A" => "T",
                        "U" => "A" }
    
    self.complement(rna, rna_complements)
  end

  private

  def self.complement(dna, complements)
    (dna.chars.collect { |base| complements[base] }).join
  end
end

class Complement

  def self.of_dna(dna_str)
    dna_str.gsub(/[#{dna_to_rna.keys.join}]/, dna_to_rna)
  end

  def self.of_rna(rna_str)
    rna_str.gsub(/[#{rna_to_dna.keys.join}]/, rna_to_dna)
  end

  def self.dna_to_rna
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.rna_to_dna
    dna_to_rna.invert
  end

end

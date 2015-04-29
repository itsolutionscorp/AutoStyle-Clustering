class Complement

  def self.of_dna(dna_str)
    dna_str.gsub(/[#{dna_hash.keys.join}]/, dna_hash)
  end

  def self.of_rna(rna_str)
    rna_str.gsub(/[#{rna_hash.keys.join}]/, rna_hash)
  end

  def self.dna_hash
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.rna_hash
    dna_hash.invert
  end

end

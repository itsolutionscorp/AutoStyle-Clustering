class Complement

  @dna_complements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(rna_strands)
    self.complements_values(@dna_complements, rna_strands);
  end

  def self.of_rna(dna_strands)
    self.complements_values(@dna_complements.invert, dna_strands);
  end

  def self.complements_values(complements, keys)
    values = '';
    keys.chars.each{ | val | values += complements.fetch(val, '') }
    return values
  end

end

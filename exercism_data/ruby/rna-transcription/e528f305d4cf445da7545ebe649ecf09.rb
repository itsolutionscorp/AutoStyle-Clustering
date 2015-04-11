class Complement

  @dna_rna_complements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(rna_strands)
    dna = '';
    rna_strands.chars.each do | val |
      dna += @dna_rna_complements[val]
    end
    return dna
  end

  def self.of_rna(dna_strands)
    rna = '';
    dna_strands.chars.each do | val |
      rna += @dna_rna_complements.key(val)
    end
    return rna
  end

end

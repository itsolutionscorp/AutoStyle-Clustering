class Complement

  def self.dna_complements
    {'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'}
  end

  def self.rna_complements
    {'A' => 'T',
    'U' => 'A',
    'G' => 'C',
    'C' => 'G'}
  end

  def self.of_dna(dna)
    return dna_complements[dna] if dna.size == 1
    return dna_complements[dna[0]] + of_dna(dna[1..-1])
  end

  def self.of_rna(rna)
    return rna_complements[rna] if rna.size == 1
    return rna_complements[rna[0]] + of_rna(rna[1..-1])
  end
end

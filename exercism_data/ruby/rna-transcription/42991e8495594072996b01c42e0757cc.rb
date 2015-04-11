class Complement
  def self.dna_to_rna
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.rna_to_dna
    Hash[self.dna_to_rna.map(&:reverse)]
  end

  def self.of_dna dna
    (dna.chars.map { |c| self.dna_to_rna[c] }).join
  end

  def self.of_rna rna
    (rna.chars.map { |c| self.rna_to_dna[c] }).join
  end

end

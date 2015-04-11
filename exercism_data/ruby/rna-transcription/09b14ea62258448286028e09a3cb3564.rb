class Complement

  def self.of_dna(dna)
    dna.chars.map { |chr| dna_rna_translation[chr] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |chr| rna_dna_translation[chr] }.join
  end

  def self.dna_rna_translation
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.rna_dna_translation
    dna_rna_translation.invert
  end
end

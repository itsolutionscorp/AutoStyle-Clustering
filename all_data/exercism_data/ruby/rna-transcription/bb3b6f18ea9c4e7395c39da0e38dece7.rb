class Complement

  @@dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.map { |c|
      @@dna_to_rna[c]
    }.join
  end

  def self.of_rna(dna)
    dna.chars.map { |c|
      @@dna_to_rna.invert[c]
    }.join
  end

end

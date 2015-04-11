class Complement

  TRANSLATION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.inject("") {|comp, nuc| comp << TRANSLATION[nuc]; comp}
  end

  def self.of_rna(rna)
    rna.chars.inject("") {|comp, nuc| comp << TRANSLATION.invert[nuc]; comp}
  end
end

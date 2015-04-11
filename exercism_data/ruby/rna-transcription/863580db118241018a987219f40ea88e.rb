class Complement

  TRANSLATION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    translate(dna.chars, TRANSLATION)
  end

  def self.of_rna(rna)
    translate(rna.chars, TRANSLATION.invert)
  end

  def self.translate(strand, translation)
    strand.inject("") {|comp, nuc| comp << translation[nuc]; comp}
  end
end

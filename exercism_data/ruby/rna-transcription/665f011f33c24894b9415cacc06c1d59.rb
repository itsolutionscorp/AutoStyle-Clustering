class Complement

  COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  INVERSE_COMPLEMENTS = COMPLEMENTS.invert

  def self.of_dna(dna)
    dna.each_char.map { |adenine| COMPLEMENTS[adenine] }.join
  end

  def self.of_rna(rna)
    rna.each_char.map { |cytosine| INVERSE_COMPLEMENTS[cytosine] }.join
  end
end

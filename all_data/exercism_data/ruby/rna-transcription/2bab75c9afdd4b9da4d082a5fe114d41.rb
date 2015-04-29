class Complement
  COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'U' => 'A',
    'A' => 'T'
  }

  def self.of_dna input
    inverts = COMPLEMENTS.invert
    input.chars.map{ |c| inverts[c] }.join
  end

  def self.of_rna input
    input.chars.map{ |c| COMPLEMENTS[c] }.join
  end
end

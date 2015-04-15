module Complement

  DnaToRna = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  RnaToDna = DnaToRna.invert

  def self.of_dna(str)
    str.chars.map{ |char| DnaToRna[char]}.join
  end

  def self.of_rna(str)
    str.chars.map{ |char| RnaToDna[char]}.join
  end

end

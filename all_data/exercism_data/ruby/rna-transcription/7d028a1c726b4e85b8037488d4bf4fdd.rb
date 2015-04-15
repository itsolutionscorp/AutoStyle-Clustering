class Complement
  COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'U' => 'A',
    'A' => 'T'
  }

  def self.of_dna input
    input.chars.map{ |c| COMPLEMENTS.invert[c] }.reduce(:+)
  end

  def self.of_rna input
    input.chars.map{ |c| COMPLEMENTS[c] }.reduce(:+)
  end
end

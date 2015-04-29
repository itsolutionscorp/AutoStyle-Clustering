class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  def self.of_dna(dna)
    dna.chars.map { |x| DNA_COMPLEMENTS[x] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |x| DNA_COMPLEMENTS.key(x) }.join
  end

end

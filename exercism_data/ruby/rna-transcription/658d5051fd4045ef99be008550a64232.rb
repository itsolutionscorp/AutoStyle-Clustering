class Complement
  COMPLEMENT = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
  }

  def self.of_dna(old_sequence)
    complement = ''
    old_sequence.chars.map{ |char| complement << COMPLEMENT[char]}
    complement
  end

  def self.of_rna(old_sequence)
    complement = ''
    old_sequence.chars.map{ |char| complement << COMPLEMENT.key(char) }
    complement
  end
end

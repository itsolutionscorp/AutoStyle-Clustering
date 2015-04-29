class Complement
  DICTIONARY = { 'rna' => 'CGTA', 'dna' => 'GCAU' }

  def self.of_dna(sequence)
    sequence.tr(DICTIONARY['rna'], DICTIONARY['dna'])
  end

  def self.of_rna(sequence)
    sequence.tr(DICTIONARY['dna'], DICTIONARY['rna'])
  end
end

class Complement

  def self.of_dna(acids)
    build_complements(acids, dna_to_rna)
  end

  def self.of_rna(acids)
    build_complements(acids, rna_to_dna)
  end

  private

  def self.build_complements(acids, mapping)
    acids.split('').inject('') do |string, acid|
      string + mapping[acid]
    end
  end

  def self.dna_to_rna
    { 'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U' }
  end

  def self.rna_to_dna
    dna_to_rna.invert
  end

end

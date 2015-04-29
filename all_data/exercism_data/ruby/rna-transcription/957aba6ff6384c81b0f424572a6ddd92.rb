class Complement

  def self.correpondance
    { 
      'C' => 'G',
      'G' => 'C' ,
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.of_dna(dna_sequence)
    rna_sequence = ''
    dna_sequence.each_char do |dna|
      rna_sequence += correpondance[dna]
    end
    return rna_sequence
  end

  def self.of_rna(rna_sequence)
    dna_sequence = ''
    rna_sequence.each_char do |rna|
      dna_sequence += correpondance.detect{|key, value| value == rna}.first
    end
    return dna_sequence
  end
end

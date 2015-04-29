class Complement
  TRANSCRIPT = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.tr TRANSCRIPT.keys.join, TRANSCRIPT.values.join
  end

  def self.of_rna(rna)
    rna.tr TRANSCRIPT.values.join, TRANSCRIPT.keys.join
  end
end

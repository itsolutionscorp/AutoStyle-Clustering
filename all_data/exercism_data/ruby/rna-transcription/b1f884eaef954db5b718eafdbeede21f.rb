class Complement
  @@transcript = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.tr @@transcript.keys.join, @@transcript.values.join
  end

  def self.of_rna(rna)
    rna.tr @@transcript.values.join, @@transcript.keys.join
  end
end

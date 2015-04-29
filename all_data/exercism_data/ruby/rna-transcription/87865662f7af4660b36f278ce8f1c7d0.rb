class Complement

  TRANSLATION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    translate(dna, TRANSLATION)
  end

  def self.of_rna(rna)
    translate(rna, TRANSLATION.invert)
  end

  def self.translate(strand, translation)
    strand.gsub(/[#{translation.keys.join}]/, translation)
  end
end

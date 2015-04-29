class Complement

  CODONS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.translate(strand, translate_table)
    strand.chars.map{ |c| translate_table[c] }.join
  end

  def self.of_dna(dna_string)
    translate(dna_string, CODONS)
  end

  def self.of_rna(rna_string)
    translate(rna_string, CODONS.invert)
  end

end

class Complement

  CODONS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.translate(translate_table, codon)
    translate_table[codon]
  end

  def self.of_dna(dna_string)
    out = dna_string.chars.map do |c|
      translate(CODONS, c)
    end
    out.join('')
  end

  def self.of_rna(rna_string)
    out = rna_string.chars.map do |c|
      translate(CODONS.invert, c)
    end
    out.join('')
  end

end

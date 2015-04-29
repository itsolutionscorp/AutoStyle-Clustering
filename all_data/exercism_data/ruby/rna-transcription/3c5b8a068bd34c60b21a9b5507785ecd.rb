class Complement
  COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_nucleotide(nuc, rna = false)
    (rna ? COMPLEMENTS.invert : COMPLEMENTS)[nuc]
  end

  def self.of_dna(dna)
    dna.chars.map { |nuc| of_nucleotide(nuc) }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |nuc| of_nucleotide(nuc, true) }.join
  end
end

class Complement

  COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(sequence)
    sequence.chars.map { |nucleotide|
      COMPLEMENTS.fetch(nucleotide)
    }.join
  end

  def self.of_rna(sequence)
    sequence.chars.map { |nucleotide|
      COMPLEMENTS.key(nucleotide)
    }.join
  end
end

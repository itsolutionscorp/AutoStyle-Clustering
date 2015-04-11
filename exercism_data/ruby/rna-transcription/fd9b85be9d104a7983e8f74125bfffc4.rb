module Complement
  extend self

  def of_dna(sequence)
    translate(sequence, dna_complements)
  end

  def of_rna(sequence)
    translate(sequence, dna_complements.invert)
  end

  protected

  def translate(sequence, complements)
    sequence.chars.map { |c| complements[c] }.join
  end

  def dna_complements
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end
end

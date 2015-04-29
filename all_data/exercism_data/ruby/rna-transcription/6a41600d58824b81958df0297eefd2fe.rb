class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna sequence
    transcribe sequence, DNA_COMPLEMENTS
  end

  def self.of_rna sequence
    transcribe sequence, RNA_COMPLEMENTS
  end

  def self.transcribe sequence, complements
    sequence.each_char.map { |nucleotide|
      complements[nucleotide]
    }.join
  end
end

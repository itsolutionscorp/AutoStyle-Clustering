class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna sequence
    map_sequence sequence, DNA_COMPLEMENTS
  end

  def self.of_rna sequence
    map_sequence sequence, RNA_COMPLEMENTS
  end

  def self.map_sequence sequence, complements
    sequence.each_char.map { |nucleotide|
      complements[nucleotide]
    }.join
  end
end

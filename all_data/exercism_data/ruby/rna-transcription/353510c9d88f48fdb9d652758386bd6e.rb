class Complement
  DICTIONARY = { 'rna' => 'CGTA', 'dna' => 'GCAU' }

  def self.of_dna(sequence)
    sequence.chars.map { |nucleotide| translate(nucleotide, 'rna', 'dna') }.join
  end

  def self.of_rna(sequence)
    sequence.chars.map { |nucleotide| translate(nucleotide, 'dna', 'rna') }.join
  end

  private

  def self.translate(nucleotide, source, target)
    translation_index = DICTIONARY[source].chars.index(nucleotide)
    DICTIONARY[target].chars[translation_index]
  end
end

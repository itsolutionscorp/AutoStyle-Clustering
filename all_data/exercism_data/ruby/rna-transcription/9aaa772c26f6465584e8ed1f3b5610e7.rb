class Complement
  DNA_TO_RNA = { 'G' => 'C',
                 'C' => 'G',
                 'T' => 'A',
                 'A' => 'U' }

  RNA_TO_DNA = { 'G' => 'C',
                 'C' => 'G',
                 'A' => 'T',
                 'U' => 'A' }

  def self.of_dna(sequence)
    translate_sequence(sequence, DNA_TO_RNA)
  end

  def self.of_rna(sequence)
    translate_sequence(sequence, RNA_TO_DNA)
  end

  def self.translate_sequence(sequence, translation_map)
    sequence.chars.collect { |char| translation_map[char] }.join('')
  end
end

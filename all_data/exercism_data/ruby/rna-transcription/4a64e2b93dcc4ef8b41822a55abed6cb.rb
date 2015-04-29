class Complement

  DNA_TO_RNA_MAPPING = { 'G' => 'C',
                         'C' => 'G',
                         'T' => 'A',
                         'A' => 'U' }

  def self.of_dna(dna_sequence)
    result = dna_sequence.chars.map { |char|
      DNA_TO_RNA_MAPPING[char] if DNA_TO_RNA_MAPPING[char]
    }.join

    raise ArgumentError if result.empty?
    result
  end

  def self.of_rna(rna_sequence)
    result = rna_sequence.chars.map { |char|
      DNA_TO_RNA_MAPPING.key(char) if DNA_TO_RNA_MAPPING.key(char)
    }.join

    raise ArgumentError if result.empty?
    result
  end
end

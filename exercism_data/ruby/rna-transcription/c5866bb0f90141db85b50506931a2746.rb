class Complement

  REPLACEMENTS_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(sequence)
    sequence.gsub(/./, REPLACEMENTS_TO_RNA)
  end

  def self.of_rna(sequence)
    sequence.gsub(/./, REPLACEMENTS_TO_RNA.invert)
  end
end

module Complement
  DNA_RNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.map { |char| DNA_RNA_MAP[char] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |char| DNA_RNA_MAP.invert[char] }.join
  end
end

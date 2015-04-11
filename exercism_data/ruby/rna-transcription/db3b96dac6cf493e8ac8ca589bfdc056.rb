module Complement

  MAP_FROM_DNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }.freeze

  MAP_FROM_RNA = {
    'G' => 'C',
    'C' => 'G',
    'U' => 'A',
    'A' => 'T'
  }.freeze

  def self.of_dna(dna)
    dna.chars.map { |nucleotide| MAP_FROM_DNA[nucleotide] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |nucleotide| MAP_FROM_RNA[nucleotide] }.join
  end
end

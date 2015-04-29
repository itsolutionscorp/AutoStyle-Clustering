module Nucleotide
  DNA_TO_RNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.invert
end

class Complement
  include Nucleotide

  def self.of_dna(strand)
    strand.chars.map { |nucleotide| DNA_TO_RNA_MAPPING[nucleotide] }.join
  end
  def self.of_rna(strand)
    strand.chars.map { |nucleotide| RNA_TO_DNA_MAPPING[nucleotide] }.join
  end
end

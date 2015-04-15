module Complement
  DNA_TO_RNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.invert

  def self.of_dna(strand)
    strand.chars.map {|c| DNA_TO_RNA_MAPPING[c]}.join
  end

  def self.of_rna(strand)
    strand.chars.map {|c| RNA_TO_DNA_MAPPING[c]}.join
  end
end

module Complement
  DNA_TO_RNA = Hash['GCAT'.chars.zip 'CGUA'.chars]
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna strand
    strand.chars.map { |char| DNA_TO_RNA[char] }.join
  end

  def self.of_rna strand
    strand.chars.map { |char| RNA_TO_DNA[char] }.join
  end
end

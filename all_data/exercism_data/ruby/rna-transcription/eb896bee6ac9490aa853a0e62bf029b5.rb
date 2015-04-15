module Complement

  DNA_TO_RNA = { 'C'=>'G', 'G'=>'C', 'T'=>'A', 'A'=>'U' }
  RNA_TO_DNA = { 'C'=>'G', 'G'=>'C', 'U'=>'A', 'A'=>'T' }

  def self.of_dna dna_string
    raise ArgumentError if dna_string.include?('U')
    dna_string.chars.map {|e| DNA_TO_RNA[e] }.join
  end

  def self.of_rna rna_string
    raise ArgumentError if rna_string.include?('T')
    rna_string.chars.map {|e| RNA_TO_DNA[e] }.join
  end
end

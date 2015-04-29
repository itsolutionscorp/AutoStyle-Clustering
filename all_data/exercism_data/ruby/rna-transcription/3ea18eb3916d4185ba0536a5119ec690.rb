module Complement

  DNA_TO_RNA = ['CGTA', 'GCAU']
  RNA_TO_DNA = DNA_TO_RNA.reverse

  def self.of_dna dna_string
    raise ArgumentError if dna_string.include?('U')
    dna_string.tr(*DNA_TO_RNA)
  end

  def self.of_rna rna_string
    raise ArgumentError if rna_string.include?('T')
    rna_string.tr(*RNA_TO_DNA)
  end
end

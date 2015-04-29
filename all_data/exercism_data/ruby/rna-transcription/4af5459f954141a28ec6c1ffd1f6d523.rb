module Complement
  DNA_TO_RNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.invert

  DNA_REGEX = /[GCTA]/
  RNA_REGEX = /[CGAU]/

  def Complement.of_dna(dna_string)
    dna_string.gsub(DNA_REGEX, DNA_TO_RNA_MAPPING)
  end

  def Complement.of_rna(rna_string)
    rna_string.gsub(RNA_REGEX, RNA_TO_DNA_MAPPING)
  end
end

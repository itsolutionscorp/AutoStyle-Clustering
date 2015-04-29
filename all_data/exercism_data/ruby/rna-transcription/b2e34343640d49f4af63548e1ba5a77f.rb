module Complement
  DNA_ALPHABET = "CGTA"
  RNA_ALPHABET = "GCAU"

  def self.of_dna(nucleotides)
    nucleotides.tr(DNA_ALPHABET, RNA_ALPHABET)
  end

  def self.of_rna(nucleotides)
    nucleotides.tr(RNA_ALPHABET, DNA_ALPHABET)
  end
end

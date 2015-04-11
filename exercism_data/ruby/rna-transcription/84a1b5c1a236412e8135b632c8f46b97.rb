# Utilities for converting between DNA and RNA complements
class Complement
  DNA_BASES = 'ATGC'
  RNA_BASES = 'UACG'

  # Convert a DNA sequence to its complementary RNA
  #
  # @param dna [String]
  # @return String
  def self.of_dna(dna)
    dna.tr(DNA_BASES, RNA_BASES)
  end

  # Convert an RNA sequence to its complementary DNA
  #
  # @param rna [String]
  # @return String
  def self.of_rna(rna)
    rna.tr(RNA_BASES, DNA_BASES)
  end
end

require "English"

class Complement
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  def self.of_dna(dna)
    if dna =~ /[^#{DNA_NUCLEOTIDES}]/
      raise ArgumentError, "#{$MATCH} is not a DNA nucleotide"
    end
    dna.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(rna)
    if rna =~ /[^#{RNA_NUCLEOTIDES}]/
      raise ArgumentError, "#{$MATCH} is not an RNA nucleotide"
    end
    rna.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end
end

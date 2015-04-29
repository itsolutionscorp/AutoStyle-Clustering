module Complement
  DNA_NUCLEOTIDES = "GCTA".freeze
  RNA_NUCLEOTIDES = "CGAU".freeze

  module_function

  def of_dna(dna_strand)
    dna_strand.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def of_rna(rna_strand)
    rna_strand.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end
end

# Thanks @kiddle - Today I learned a lot!
module Complement
  DNA_nucleotide = 'GCTA'
  RNA_nucleotide = 'CGAU'

  def self.of_dna(strand)
    strand.tr DNA_nucleotide, RNA_nucleotide
  end

  def self.of_rna(strand)
    strand.tr RNA_nucleotide, DNA_nucleotide
  end
end

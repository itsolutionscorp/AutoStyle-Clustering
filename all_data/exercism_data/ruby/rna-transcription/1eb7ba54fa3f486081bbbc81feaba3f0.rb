module Complement
  class << self

    DNA_nucleotide = 'GCTA'
    RNA_nucleotide = 'CGAU'

    def of_dna(strand)
      strand.tr DNA_nucleotide, RNA_nucleotide
    end

    def of_rna(strand)
      strand.tr RNA_nucleotide, DNA_nucleotide
    end
  end
end

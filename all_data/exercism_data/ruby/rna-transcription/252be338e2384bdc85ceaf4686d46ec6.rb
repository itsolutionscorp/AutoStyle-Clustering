module Complement
  class << self
    def dna_nucleotides
      "GCTA"
    end

    def rna_nucleotides
      "CGAU"
    end

    def of_dna(strand)
      strand.tr(dna_nucleotides, rna_nucleotides)
    end

    def of_rna(strand)
      strand.tr(rna_nucleotides, dna_nucleotides)
    end
  end
end

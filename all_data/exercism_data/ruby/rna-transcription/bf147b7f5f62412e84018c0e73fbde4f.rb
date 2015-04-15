# Computes complements to string representations of dna and rna
class Complement
  class << self
    DNA_TO_RNA = %w(CGTA GCAU)
    def of_dna(strand)
      strand.tr(*DNA_TO_RNA)
    end

    def of_rna(strand)
      strand.tr(*DNA_TO_RNA.reverse)
    end
  end
end

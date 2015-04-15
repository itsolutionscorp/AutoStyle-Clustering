class Complement
  def self.of_dna(strand)
    strand.tr(dna_nucleotides, rna_nucleotides)
  end

  def self.of_rna(strand)
    strand.tr(rna_nucleotides, dna_nucleotides)
  end

  class << self
    private

    def dna_nucleotides
      'CGAT'
    end

    def rna_nucleotides
      'GCUA'
    end
  end
end

class Complement
  class << self
    IN_DNA = 'GCTA'
    IN_RNA = 'CGAU'

    def of_dna(dna)
      fail ArgumentError if invalid_dna?(dna)

      dna.tr(IN_DNA, IN_RNA)
    end

    def of_rna(rna)
      fail ArgumentError if invalid_rna?(rna)

      rna.tr(IN_RNA, IN_DNA)
    end

    private

    def invalid_dna?(strand)
      strand =~ /[^GCTA]/
    end

    def invalid_rna?(strand)
      strand =~ /[^CGAU]/
    end
  end
end

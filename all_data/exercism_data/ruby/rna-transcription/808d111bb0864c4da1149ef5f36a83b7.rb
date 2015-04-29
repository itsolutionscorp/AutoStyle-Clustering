class Complement
  class << self
    DNA = "GCTA"
    RNA = "CGAU"

    def of_dna(strand)
      strand.tr(DNA, RNA)
    end

    def of_rna(strand)
      strand.tr(RNA, DNA)
    end
  end
end

class Complement

  class << self 

    DNA_NUCLEOTIDES = 'GCTA'
    RNA_NUCLEOTIDES = 'CGAU'

    def of_dna(strand)
      strand.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
    end

    def of_rna(strand)
      strand.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
    end

  end

end

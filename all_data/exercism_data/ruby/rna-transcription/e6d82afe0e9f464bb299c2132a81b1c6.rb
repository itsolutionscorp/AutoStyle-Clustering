class Complement
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  class << self
    def of_dna( dna )
      dna.tr DNA_NUCLEOTIDES, RNA_NUCLEOTIDES
    end

    def of_rna( rna )
      rna.tr RNA_NUCLEOTIDES, DNA_NUCLEOTIDES
    end
  end
end

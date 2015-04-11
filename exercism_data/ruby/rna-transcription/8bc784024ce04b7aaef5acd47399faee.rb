class Complement

  DNA_KEY = 'GCTA'
  RNA_KEY = 'CGAU'

  class << self
    def of_dna(rna_strand)
      rna_strand.tr(DNA_KEY, RNA_KEY)
    end

    def of_rna(dna_strand)
      dna_strand.tr(RNA_KEY, DNA_KEY)
    end
  end

end

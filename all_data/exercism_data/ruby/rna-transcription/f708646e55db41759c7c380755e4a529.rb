class Complement

  RNA = 'GCTA'
  DNA = 'CGAU'

  class << self
    def of_dna(strand)
      strand.tr(RNA, DNA) 
    end

    def of_rna(strand)
      strand.tr(DNA, RNA) 
    end

  end
  

end

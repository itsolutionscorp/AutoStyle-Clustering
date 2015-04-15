class Complement
  class << self
    def of_dna strand 
      transcribe strand, to_rna 
    end

    def of_rna strand 
      transcribe strand, to_dna 
    end

  private

    def to_dna
      ['AUCG', 'TAGC']
    end

    def to_rna
      to_dna.reverse
    end

    def transcribe strand, key 
      strand.tr *key 
    end
  end
end

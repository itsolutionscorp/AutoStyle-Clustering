class Complement
  class << self
    def of_dna(strand)
      transcribe strand, to_rna 
    end

    def of_rna(strand)
      transcribe strand, to_dna 
    end

  private

    def sequence(strand)
      strand.chars
    end

    def strand(sequence)
      sequence.reduce('', :+)
    end

    def to_dna
      { 'A' => 'T', 'U' => 'A', 'C' => 'G', 'G' => 'C' }
    end

    def to_rna
      to_dna.reject { |base, _| base == 'U' }.merge({ 'A' => 'U', 'T' => 'A' })
    end

    def transcribe(strand, complement)
      strand(sequence(strand).map { |base| complement[base] })
    end
  end
end

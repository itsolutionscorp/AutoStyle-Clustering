class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  class << self
    def of_dna(strand)
      complements(strand, DNA_COMPLEMENTS)
    end

    def of_rna(strand)
      complements(strand, RNA_COMPLEMENTS)
    end
  end

  private

  def self.complements(strand, complements)
    strand.chars.map { |nucleotide|
      complements[nucleotide]
    }.join('')
  end
end

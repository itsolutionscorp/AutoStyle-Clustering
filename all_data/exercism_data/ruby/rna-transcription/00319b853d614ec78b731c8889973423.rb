class Complement
  DNA_COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  class << self
    def of_dna(strand)
      strand.gsub(/[#{DNA_COMPLEMENTS.keys}]/, DNA_COMPLEMENTS)
    end

    def of_rna(strand)
      strand.gsub(/[#{RNA_COMPLEMENTS.keys}]/, RNA_COMPLEMENTS)
    end
  end
end

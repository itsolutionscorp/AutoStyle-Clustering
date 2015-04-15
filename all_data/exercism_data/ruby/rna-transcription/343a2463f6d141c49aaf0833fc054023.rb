module Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'U' => 'A',
    'A' => 'T'
  }

  class << self
    def of_dna(dna)
      nucleotide_complement(dna, DNA_COMPLEMENTS)
    end

    def of_rna(rna)
      nucleotide_complement(rna, RNA_COMPLEMENTS)
    end

    private

    def nucleotide_complement(strand, complements)
      strand.length.times do |i|
        strand[i] = complements[strand[i]] unless complements[strand[i]].nil?
      end
      strand
    end
  end
end

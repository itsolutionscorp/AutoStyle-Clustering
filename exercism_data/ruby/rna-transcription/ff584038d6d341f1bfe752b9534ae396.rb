class Complement

  COMPLEMENTS = [['G', 'C'], ['C', 'G'], ['T', 'A'], ['A', 'U']]

  class << self

    def of_dna(dna)
      complement(dna) { |nucleotide| dna_complement(nucleotide) }
    end

    def of_rna(rna)
      complement(rna) { |nucleotide| rna_complement(nucleotide) }
    end

    private

    def complement(strand, &block)
      strand.chars.map do |nucleotide|
        block.call(nucleotide)
      end.join('')
    end

    def dna_complement(nucleotide)
      COMPLEMENTS.assoc(nucleotide).last
    end

    def rna_complement(nucleotide)
      COMPLEMENTS.rassoc(nucleotide).first
    end

  end

end

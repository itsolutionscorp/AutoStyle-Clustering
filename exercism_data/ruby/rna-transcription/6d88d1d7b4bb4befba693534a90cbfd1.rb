class Complement

  DNA_COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

	class << self
    def of_dna strand
    	convert strand, DNA_COMPLEMENTS
    end

    def of_rna strand
    	convert strand, RNA_COMPLEMENTS
    end

    def convert strand, conversions
     	strand.chars.map { |nucleotide| conversions[nucleotide] }.join
    end

  end

end

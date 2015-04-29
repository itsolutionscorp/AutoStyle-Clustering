class Complement
  class << self
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

    def of_dna(dna)
      complements_of(dna, DNA_COMPLEMENTS)
    end

    def of_rna(rna)
      complements_of(rna, RNA_COMPLEMENTS)
    end

    private

    def complements_of(element, type)
      element.each_char.each_with_object('') do |nucleotide, complements|
        complements << type[nucleotide]
      end
    end
  end
end

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

    def of_dna(sequence)
      complements_of(sequence, DNA_COMPLEMENTS)
    end

    def of_rna(sequence)
      complements_of(sequence, RNA_COMPLEMENTS)
    end

    private

    def complements_of(sequence, type)
      sequence.each_char.each_with_object('') do |nucleotide, complements|
        complements << type[nucleotide]
      end
    end
  end
end

class Complement
  class << self
    def of_dna(strand)
      complements(strand, :dna)
    end

    def of_rna(strand)
      complements(strand, :rna)
    end
  end

  private

  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  class << self
    def complements(strand, type)
      complements = complements_mapping(type)
      strand.chars.map { |nucleotide|
        complements[nucleotide]
      }.join('')
    end

    def complements_mapping(type)
      case type
      when :dna then DNA_COMPLEMENTS
      when :rna then RNA_COMPLEMENTS
      else raise ArgumentError, "Strand type must be either :dna or :rna"
      end
    end
  end
end

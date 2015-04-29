class Complement
  class << self
    
    DNA_COMPLIMENTS = {
      'G' => 'C',
      'T' => 'A',
      'A' => 'U',
      'C' => 'G',
      'U' => 'A'
    }

    RNA_COMPLIMENTS = {
      'C' => 'G',
      'G' => 'C',
      'U' => 'A',
      'A' => 'T'
    }

    def of_dna(dna_string)
      generate_compliments(DNA_COMPLIMENTS, dna_string)
    end

    def of_rna(rna_string)
      generate_compliments(RNA_COMPLIMENTS, rna_string)
    end

    private
      def generate_compliments(map, string)
        string.chars.map { |char| map[char] }.join
      end

  end
end

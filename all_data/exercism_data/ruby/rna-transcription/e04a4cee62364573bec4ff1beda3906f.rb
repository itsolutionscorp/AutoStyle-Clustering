class Complement

  class << self

    DNA_TO_RNA_MAPPING = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    def of_dna strand
      complement strand, true
    end

    def of_rna strand
      complement strand, false
    end

    private

    def complement strand, to_rna
      raise ArgumentError, 'strand must be a string' unless strand.is_a? String
      map = to_rna ? DNA_TO_RNA_MAPPING : DNA_TO_RNA_MAPPING.invert
      complement = ''
      strand.upcase.each_char do |nucleotide|
        complement += map[nucleotide]
      end
      complement
    end

  end

end

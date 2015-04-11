class Complement

  DNA_TO_RNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  class << self

    def of_dna strand
      complement strand, DNA_TO_RNA_MAPPING
    end

    def of_rna strand
      complement strand, DNA_TO_RNA_MAPPING.invert
    end

    private

    def complement strand, mapping
      raise ArgumentError, 'strand must be a string' unless strand.is_a? String
      strand.upcase.split(//).reduce('') {|c,n| c + mapping[n] }
    end

  end

end

class Complement

  DNA_TO_RNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  class << self

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
      strand.upcase.split(//).reduce('') {|c,n| c + map[n] }
    end

  end

end

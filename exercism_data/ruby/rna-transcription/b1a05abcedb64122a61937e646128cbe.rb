class Complement

  class << self 

    NUCLEOTIDE_CONVERSIONS = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    def of_dna(strand)
      convert_strand(strand)
    end

    def of_rna(strand)
      convert_strand(strand, from: 'rna')
    end

    private
    
    def convert_strand(strand, from: 'dna')
      strand.upcase.chars.map do |nucleotide|
        convert_nucleotide(nucleotide, from: from) 
      end.join
    end

    def convert_nucleotide(nt, from: 'dna')
      from == 'dna' ?  NUCLEOTIDE_CONVERSIONS[nt] : NUCLEOTIDE_CONVERSIONS.key(nt)
    end

  end

end

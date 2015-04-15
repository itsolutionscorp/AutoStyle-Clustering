module DNA
  class DNA
    attr_reader :strand

    KNOWN_NUCLEOTIDES = { 'A' => Nucleotides::Adenine, 'C' => Nucleotides::Cytosine, 'G' => Nucleotides::Guanine, 'T' => Nucleotides::Thymidine }
    
    def initialize strand
      @KNOWN_NUCLEOTIDES = self.KNOWN_NUCLEOTIDES
      @strand = strand
    end

    def complement
      @complement ||= @strand.chars.map { |n| @KNOWN_NUCLEOTIDES[n].complement.to_s }.join
    end

    def self.complement strand
      strand.chars.map { |n| KNOWN_NUCLEOTIDES[n].complement.to_s }.join
    end
  end
end

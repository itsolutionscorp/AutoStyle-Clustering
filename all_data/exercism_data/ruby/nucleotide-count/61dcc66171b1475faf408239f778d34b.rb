class DNA
  attr_reader :strand

  DNA_NUCLEOTIDES = {
    adenosine: ?A,
    cytosine:  ?C,
    guanine:   ?G,
    thymine:   ?T,
  }

  RNA_NUCLEOTIDES = {
    adenosine: ?A,
    cytosine:  ?C,
    guanine:   ?G,
    uracil:    ?U,
  }

  VALID_NUCLEOTIDE_CODES = DNA_NUCLEOTIDES.merge(RNA_NUCLEOTIDES).invert

  def initialize(strand)
    @strand = strand.upcase
  end

  def count(nucleotide)
    VALID_NUCLEOTIDE_CODES.fetch(nucleotide) do
      raise ArgumentError, "No such nucleotide: #{nucleotide}"
    end 
    strand.count nucleotide
  end

  def nucleotide_counts
    Hash[
      DNA_NUCLEOTIDES.each_value.map do |nucleotide| 
        [nucleotide, strand.count(nucleotide)]
      end
    ]
  end

end

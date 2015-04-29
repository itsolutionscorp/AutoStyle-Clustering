class DNA
  NUCLEOTIDES=[ 'A', 'C', 'G', 'T', 'U' ]
  DNA_NUCLEOTIDES=NUCLEOTIDES-[ 'U' ]
  RNA_NUCLEOTIDES=NUCLEOTIDES-[ 'T' ]

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    @strand.count(nucleotide)
  end

  def nucleotide_counts()
    DNA_NUCLEOTIDES.reduce(Hash.new(0)) { |counts, nucleotide|
      counts[nucleotide] = count(nucleotide)
      counts
    }
  end

  private
  def validate_nucleotide(nucleotide)
    if (invalid_nucleotide(nucleotide))
      raise ArgumentError
    end
  end

  def invalid_nucleotide(nucleotide)
    !(DNA_NUCLEOTIDES | RNA_NUCLEOTIDES).include?(nucleotide)
  end
end

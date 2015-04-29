class DNA
  VALID_NUCLEOTIDS = %w( A T C G )

  def initialize strand
    raise ArgumentError if strand.chars.any?{ |nucleotide| !VALID_NUCLEOTIDS.include? nucleotide }
    @strand = strand
  end

  def count nucleotide
    raise ArgumentError unless VALID_NUCLEOTIDS.include? nucleotide
    @strand.count nucleotide
  end

  def nucleotide_counts
    Hash[VALID_NUCLEOTIDS.map { |nucleotide| [nucleotide, count(nucleotide)] }]
  end
end

class DNA
  NUCLEOTIDES = %w(A C G T U)
  DNA_NUCLEOTIDES = %w(A C G T)

  attr_reader :strand

  def initialize(strand)
    @strand = strand.chars
    raise ArgumentError, "Contains invalid DNA nucleotides" unless valid_strand?
  end

  def count(nucleotide)
    raise ArgumentError, "Invalid nucleotide" unless NUCLEOTIDES.include?(nucleotide)
    strand.count(nucleotide)
  end

  def nucleotide_counts
    Hash[DNA_NUCLEOTIDES.zip(DNA_NUCLEOTIDES.map { |n| count(n) })]
  end

  def valid_strand?
    @strand.all? { |nucleotide| DNA_NUCLEOTIDES.include?(nucleotide) }
  end
end

class DNA
  NUCLEOTIDES = %w{ A C T G U }

  def initialize(chain)
    @chain = chain
  end

  def nucleotide_counts
    { 'A' => @chain.count('A'),
      'C' => @chain.count('C'),
      'T' => @chain.count('T'),
      'G' => @chain.count('G') }
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @chain.count(nucleotide)
  end
end

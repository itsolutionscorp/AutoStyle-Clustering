class DNA
  NUCLEOTIDES = %w{ A C T G U }

  def initialize(chain)
    @chain = chain
  end

  def nucleotide_counts
    { 'A' => count('A'),
      'C' => count('C'),
      'T' => count('T'),
      'G' => count('G') }
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @chain.count(nucleotide)
  end
end

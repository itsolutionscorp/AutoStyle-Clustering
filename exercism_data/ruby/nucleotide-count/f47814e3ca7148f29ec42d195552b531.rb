class DNA
  VALID_NUCLEOTIDES = %w[A T C G]

  def initialize(string)
    @string = string
    raise ArgumentError unless valid?
  end

  def count(nucleotide)
    raise ArgumentError unless VALID_NUCLEOTIDES.include? nucleotide
    @string.count(nucleotide)
  end

  def nucleotide_counts
    Hash[VALID_NUCLEOTIDES.map { |nuc| [nuc, count(nuc)] }]
  end

  private

  def valid?
    @string =~ /^#{Regexp.union(VALID_NUCLEOTIDES)}*$/
  end
end

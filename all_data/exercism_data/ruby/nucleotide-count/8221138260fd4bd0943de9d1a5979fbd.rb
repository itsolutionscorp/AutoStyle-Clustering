class DNA
  NUCLEOTIDES = %w(A T C G)

  def initialize string
    raise ArgumentError unless /\A[ATCG]*\z/ =~ string
    @string = string
  end

  def count nucleotide
    raise ArgumentError unless NUCLEOTIDES.include? nucleotide
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    Hash[NUCLEOTIDES.map { |n| [n, @string.count(n)] }]
  end
end

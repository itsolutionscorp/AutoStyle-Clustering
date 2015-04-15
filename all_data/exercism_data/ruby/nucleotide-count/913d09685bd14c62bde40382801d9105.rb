class DNA

  NUCLEOTIDES = "ATCG"

  def initialize(string)
    raise ArgumentError if bad_genes(string) && !string.empty?
    @string = string
  end

  def count(nucleotide)
    raise ArgumentError if bad_genes(nucleotide)
    @string.count(nucleotide)
  end

  def nucleotide_counts
    Hash[*NUCLEOTIDES.chars.map {|k| [k,count(k)]}.flatten]
  end

  private

  def bad_genes(strand)
    strand =~ Regexp.new("[^#{NUCLEOTIDES}]")
  end

end

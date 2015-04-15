class DNA

  NUCLEOTIDES = "ATCG"

  def initialize(string)
    raise ArgumentError unless good_genes(string) || string.empty?
    @string = string
  end

  def count(nucleotide)
    raise ArgumentError unless good_genes(nucleotide)
    @string.count(nucleotide)
  end

  def nucleotide_counts
    Hash[*NUCLEOTIDES.chars.map {|k| [k,count(k)]}.flatten]
  end

  private

  def good_genes(strand)
    strand =~ Regexp.new("^[#{NUCLEOTIDES}]+")
  end

end

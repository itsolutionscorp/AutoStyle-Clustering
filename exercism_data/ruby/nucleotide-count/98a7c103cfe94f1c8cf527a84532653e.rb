class Nucleotide

  VALID_NUCLEOTIDES = "ATCG"

  def initialize(dna)
    @dna = dna
  end

  def histogram
    VALID_NUCLEOTIDES.chars.map { |n| [n, count(n)] }.to_h
  end

  def self.from_dna(dna)
    raise ArgumentError if /[^#{VALID_NUCLEOTIDES}]/ =~ dna
    new(dna)
  end

  def count(c)
    @dna.count(c)
  end
end

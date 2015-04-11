class Nucleotide

  NUCLEOTIDES = %w(A C G T)

  def initialize(dna)
    @dna = dna
  end

  def self.from_dna(dna)
    raise ArgumentError if dna.match(/[^#{NUCLEOTIDES.join}]/)
    new(dna)
  end

  def count(n)
    @dna.count(n)
  end

  def histogram
    NUCLEOTIDES.each_with_object({}) { |n, h| h[n] = count(n) }
  end

end

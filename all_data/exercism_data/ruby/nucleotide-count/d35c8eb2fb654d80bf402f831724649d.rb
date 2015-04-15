class Nucleotide
  NUCLEOTIDES = %w( A C G T )

  attr_reader :histogram

  def self.from_dna(dna)
    new(dna.each_char)
  end

  def initialize(dna)
    fail ArgumentError, "#{dna} is not DNA" unless dna?(dna)

    @histogram = Hash[NUCLEOTIDES.map { |n| [n, dna.count(n)] }]
  end

  def count(nucleotide)
    histogram.fetch(nucleotide)
  end

  private

  def dna?(dna)
    (dna.to_a - NUCLEOTIDES).empty?
  end
end

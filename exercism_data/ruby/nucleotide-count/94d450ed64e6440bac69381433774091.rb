class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  NUCLEOTIDES = DNA_NUCLEOTIDES + ['U']

  attr_reader :nucleotide_counts

  def initialize dna
    raise ArgumentError unless valid_dna? dna

    @nucleotide_counts = Hash[DNA_NUCLEOTIDES.collect { |n| [n, dna.count(n)] }]
    @nucleotide_counts.default = 0
  end

  def count nucleotide
    raise ArgumentError unless nucleotide? nucleotide

    @nucleotide_counts[nucleotide]
  end

  private

  def valid_dna? dna
    (dna.chars.to_a - DNA_NUCLEOTIDES).empty?
  end

  def nucleotide? nucleotide
    NUCLEOTIDES.include? nucleotide
  end
end

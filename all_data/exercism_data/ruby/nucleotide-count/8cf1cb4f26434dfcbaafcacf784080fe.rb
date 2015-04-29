class DNA
  NUCLEOTIDES = %w(A C G T U)
  DNA_NUCLEOTIDES = %w(A C G T)

  attr_reader :genome

  def initialize(genome)
    @genome = genome.chars
    raise ArgumentError, "Contains invalid DNA nucleotides" unless valid_genome?
  end

  def count(nucleotide)
    raise ArgumentError, "Invalid nucleotide" unless NUCLEOTIDES.include?(nucleotide)
    genome.count(nucleotide)
  end

  def nucleotide_counts
    Hash[DNA_NUCLEOTIDES.zip(DNA_NUCLEOTIDES.map { |n| count(n) })]
  end

  def valid_genome?
    @genome.all? { |nucleotide| DNA_NUCLEOTIDES.include?(nucleotide) }
  end
end

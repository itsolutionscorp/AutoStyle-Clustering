class DNA
  NUCLEOTIDES = %w[A T C G]

  attr_reader :sequence
  def initialize(dna_string)
    @sequence = valid_string(dna_string).chars
  end

  def nucleotide_counts
    Hash[NUCLEOTIDES.map {|n| [n, count(n)] }]
  end

  def count(nucleotide)
    sequence.count(valid_string(nucleotide))
  end

  private

  def valid_string(dna_string)
    raise ArgumentError unless (dna_string.chars.uniq - NUCLEOTIDES).empty?
    dna_string
  end
end

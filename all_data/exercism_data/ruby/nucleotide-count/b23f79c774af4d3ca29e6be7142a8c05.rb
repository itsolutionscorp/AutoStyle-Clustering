class DNA

  DNA = %w(A C G T)
  RNA = %w(A C G U)
  NUCLEOTIDES = RNA | DNA

  attr_reader :dna

  def initialize(dna)
    @dna = dna.to_s.upcase.chars
    raise ArgumentError if (@dna.uniq - DNA).count > 0
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)

    dna.count(nucleotide)
  end

  def nucleotide_counts
    Hash[*DNA.map {|n| [n, count(n) ] }.flatten]
  end

end

class DNA

  def initialize(dna)
    raise ArgumentError if dna =~ /[^ATCG]/
    @dna = dna
  end

  def count(nucleotide)
    raise ArgumentError unless ALL_NUCLEOTIDES.include?(nucleotide)

    dna_without_nucleotide = @dna.dup.delete(nucleotide)
    @dna.length - dna_without_nucleotide.length
  end

  def nucleotide_counts
    Hash[DNA_NUCLEOTIDES.map { |n| [n, count(n)] }]
  end

  private

  ALL_NUCLEOTIDES = %w(A T C G U)
  DNA_NUCLEOTIDES = %w(A T C G)

end

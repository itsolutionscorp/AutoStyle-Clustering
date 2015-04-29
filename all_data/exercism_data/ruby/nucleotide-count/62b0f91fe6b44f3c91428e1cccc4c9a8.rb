class DNA
  NUCLEOTIDES = %w(A T C G)
  COUNTABLE_NUCLEOTIDES = NUCLEOTIDES + %w(U)

  attr_reader :strand

  def initialize(strand)
    @strand = strand
    assert_valid_strand
  end

  def count(nucleotide)
    assert_included(nucleotide, COUNTABLE_NUCLEOTIDES)
    strand.chars.count(nucleotide)
  end

  def nucleotide_counts
    Hash[NUCLEOTIDES.map { |n| [n, count(n)] }]
  end

  private

  def assert_valid_strand
    strand.chars.each do |nucleotide|
      assert_included(nucleotide, NUCLEOTIDES)
    end
  end

  def assert_included(nucleotide, valid_nucleotides)
    raise ArgumentError unless valid_nucleotides.include?(nucleotide)
  end
end

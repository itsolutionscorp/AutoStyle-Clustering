class DNA
  NUCLEOTIDES = %w(A T C G)
  ALL_NUCLEOTIDES = NUCLEOTIDES + [?U]

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.chars
  end

  def count(nucleotide)
    raise ArgumentError unless ALL_NUCLEOTIDES.include?(nucleotide)

    sequence.count { |i| i == nucleotide }
  end

  def nucleotide_counts
    sequence.each_with_object(init_counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  private

  def init_counts
    values = NUCLEOTIDES.map { |n| [n, 0] }

    Hash[values]
  end
end

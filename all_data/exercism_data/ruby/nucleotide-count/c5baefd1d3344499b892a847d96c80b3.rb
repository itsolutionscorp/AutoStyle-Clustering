class DNA
  NUCLEOTIDES = %w(A T C G U)

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.chars
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)

    sequence.count { |i| i == nucleotide }
  end

  def nucleotide_counts
    sequence.each_with_object(init_counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  private

  def init_counts
    {
      'A' => 0,
      'T' => 0,
      'C' => 0,
      'G' => 0,
    }
  end
end

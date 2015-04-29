class DNA
  NUCLEOTIDES = %w(A T C G)
  URACIL      = %w(U)

  def initialize(sequence)
    @nucleotides = sequence.each_char.to_a
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotides? nucleotide

    @nucleotides.count nucleotide
  end

  def nucleotide_counts
    NUCLEOTIDES.each_with_object({}) do |nucleotide, histogram|
      histogram[nucleotide] = count(nucleotide)
    end
  end

  private
    def valid_nucleotides?(nucleotide)
      (NUCLEOTIDES + URACIL).include? nucleotide
    end
end

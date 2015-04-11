class DNA
  DNA_NUCLEOTIDES = %w(A C G T)
  RNA_NUCLEOTIDES = %w(A C G U)
  NUCLEOTIDES     = DNA_NUCLEOTIDES + RNA_NUCLEOTIDES

  def initialize(sequence)
    @nucleotides = sequence.each_char.to_a
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotides? nucleotide

    @nucleotides.count nucleotide
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, histogram|
      histogram[nucleotide] = count(nucleotide)
    end
  end

  private
    def valid_nucleotides?(nucleotide)
      NUCLEOTIDES.include? nucleotide
    end
end

class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  RNA_NUCLEOTIDES = %w(A C G U)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES | RNA_NUCLEOTIDES

  attr_reader :nucleotides

  def initialize(nucleotides)
    @nucleotides = validate(nucleotides, DNA_NUCLEOTIDES)
  end

  def count(nucleotide)
    nucleotides.count(validate(nucleotide, ALL_NUCLEOTIDES))
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, nucleotides|
      nucleotides[nucleotide] = count(nucleotide)
    end
  end

  private

  def validate(sequence, nucleotides)
    sequence.tap do |sequence|
      sequence.chars { |c| raise ArgumentError unless nucleotides.include?(c) }
    end
  end
end

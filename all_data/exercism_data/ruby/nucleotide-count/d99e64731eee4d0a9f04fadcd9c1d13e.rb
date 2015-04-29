class DNA
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def count(nucleotide)
    raise ArgumentError.new(nucleotide) unless VALID_NUCLEOTIDES.include? nucleotide
    nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new) do |nucleotide, result|
      result[nucleotide] = count(nucleotide)
    end
  end

  private

  DNA_NUCLEOTIDES = %w(A T C G)
  VALID_NUCLEOTIDES = DNA_NUCLEOTIDES + %w(U)

  attr_reader :nucleotides
end

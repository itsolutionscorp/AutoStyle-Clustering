class NucleicAcid
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def count(nucleotide)
    raise ArgumentError.new(nucleotide) unless NUCLEOBASES.include? nucleotide
    nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    specific_nucleobases.each_with_object({}) do |nucleotide, result|
      result[nucleotide] = count(nucleotide)
    end
  end

  protected

  def specific_nucleobases
    NUCLEOBASES
  end

  private

  NUCLEOBASES = %w(A T C G U)

  attr_reader :nucleotides
end

class DNA < NucleicAcid

  protected

  def specific_nucleobases
    %w(A T C G)
  end

end

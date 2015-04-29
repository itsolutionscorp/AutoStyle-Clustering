class DNA

  NUCLEOTIDES_TYPES = "ATCGU"
  DNA_NUCLEOTIDES_TYPES = "ATCG"
  attr_reader :sequence

  def initialize sequence
    @sequence = sequence
    raise ArgumentError if invalid_sequence?(@sequence, DNA_NUCLEOTIDES_TYPES)
  end

  def count nucleotide
    raise ArgumentError if invalid_sequence?(nucleotide, NUCLEOTIDES_TYPES)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES_TYPES.chars.each_with_object({}) { |nucleotide, result|
      result[nucleotide] = count(nucleotide)
    }
  end

  private

  def invalid_sequence? sequence, nucleotide_types
    /[^#{nucleotide_types}]/ =~ sequence
  end

end

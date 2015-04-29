class DNA

  NUCLEOTIDES = "ATCGU"
  DNA_NUCLEOTIDES = "ATCG"
  attr_reader :sequence

  def initialize sequence
    @sequence = sequence
    raise ArgumentError if invalid_sequence?
  end

  def count nucleotide
    raise ArgumentError if invalid_nucleotides?(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.chars.each_with_object({}) { |nucleotide, result|
      result[nucleotide] = count(nucleotide)
    }
  end

  private

  def invalid_nucleotides? nucleotide
    if (/[^#{NUCLEOTIDES}]/ =~ nucleotide)
      true
    else
      false
    end
  end

  def invalid_sequence?
    if (/[^#{DNA_NUCLEOTIDES}]/ =~ @sequence)
      true
    else
      false
    end
  end

end

class Nucleotide

  def self.from_dna(strand, &block)
    Strand.new(strand)
  end

end

class Strand

  def initialize(strand)
    @strand = validate(strand)
  end

  VALID_NUCLEOTIDES = %w(A T C G)

  def count(nucleotide)
    nucleotides.count { |element| element == nucleotide }
  end

  def histogram
    nucleotides.each_with_object(empty_count) do |nucleotide, histogram|
      histogram[nucleotide] += 1
    end
  end

  private

  def nucleotides
    @strand.chars
  end

  def empty_count
    VALID_NUCLEOTIDES.each_with_object({}) do |nucleotide, histogram|
      histogram[nucleotide] = 0
    end
  end

  def validate(strand)
    return strand if valid_strand?(strand)
    raise ArgumentError
  end

  def valid_strand?(strand)
    strand.chars.reject do |nucleotide|
      VALID_NUCLEOTIDES.include? nucleotide
    end.empty?
  end

end

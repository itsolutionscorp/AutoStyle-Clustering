class DNA
  NUCLEOTIDES = %w{ A C G T U }
  DNA_ACIDS = %w{ A C G T }

  def initialize(strand)
    @strands = strand.chars
    validate_strand DNA_ACIDS, @strands, "Invalid sequence '#{strand}'"
  end

  def count(acids)
    validate_strand NUCLEOTIDES, acids.chars, "Invalid strand '#{acids}'"
    @strands.count(acids)
  end

  def nucleotide_counts
    DNA_ACIDS.each_with_object({}) { |a, value| value[a] = count(a) }
  end

  private
  def validate_strand(valid, acids, e = "")
    raise ArgumentError, e unless acids.all? { |acid| valid.include?(acid) }
  end
end

class DNA
  NUCLEOTIDES = %w{ A C G T U }
  DNA_ACIDS = %w{ A C G T }

  def initialize(strand)
    @strand = strand
    validate_strand DNA_ACIDS, @strand, "Invalid sequence '#{strand}'"
  end

  def count(nucleotide)
    validate_strand NUCLEOTIDES, nucleotide, "Invalid strand '#{nucleotide}'"
    @strand.chars.count(nucleotide)
  end

  def nucleotide_counts
    DNA_ACIDS.each_with_object(Hash.new()) { |a, value| value[a] = count(a) }
  end

  def validate_strand(valid, acid, error)
    raise ArgumentError, error unless acid.chars.all? { |a| valid.include?(a) }
  end
end

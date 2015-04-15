class DNA
  NUCLEOTIDES = %w{ A C G T U }
  DNA_ACIDS = %w{ A C G T }

  def initialize(strand)
    @strand = strand.chars
    validate_strand DNA_ACIDS, @strand, "Invalid sequence '#{strand}'"
  end

  def count(acids)
    validate_strand NUCLEOTIDES, acids.chars, "Invalid strand '#{acids}'"
    @strand.count acids 
  end

  def nucleotide_counts
    DNA_ACIDS.each_with_object({}) { |a, value| value[a] = count a }
  end

  def hamming_distance(strand)
    zipped_acids(strand.chars).count { |a, b| a != b }
  end

  def equal_num_of_acids(comparison)
    @strand.take comparison.size
  end

  def zipped_acids(comparison)
    equal_num_of_acids(comparison).zip comparison
  end

  private
  def validate_strand(valid_acids, acids, e)
    raise ArgumentError, e unless acids.all? { |a| valid_acids.include? a }
  end
end

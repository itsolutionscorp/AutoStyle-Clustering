class DEOXYRIBONUCLEIC
  ACIDS = %w{ A C G T U }

  def initialize(strand)
    raise "Invalid superclass call" unless @acids
    @strand = strand
    validate_strand @acids, @strand, "Invalid sequence '#{strand}'"
  end

  def count(strand)
    validate_strand ACIDS, strand, "Invalid strand '#{strand}'"
    @strand.chars.count(strand)
  end

  def nucleotide_counts
    @acids.each_with_object(Hash.new()) { |a, value| value[a] = count(a) }
  end

  def validate_strand(valid, acid, error)
    raise ArgumentError, error unless acid.chars.all? { |a| valid.include?(a) }
  end
end

class DNA < DEOXYRIBONUCLEIC
  def initialize(strand)
    @acids = %w{ A C G T }
    super
  end
end

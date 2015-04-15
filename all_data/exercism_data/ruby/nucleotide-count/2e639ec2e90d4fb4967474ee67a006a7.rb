class DEOXYRIBONUCLEIC
  attr_accessor :nucleotide_counts

  ACIDS = %w{ A C G T U }

  def initialize(strand)
    @strand = strand
    validate_strand? @acids, @strand, "Invalid sequence '#{strand}'"

    @nucleotide_counts = @acids.each_with_object(Hash.new()) do |acid, values| 
      values[acid] ||= 0
    end
    @strand.chars.each { |acid| @nucleotide_counts[acid] += 1 }
  end

  def count(strand)
    validate_strand? ACIDS, strand, "Invalid strand '#{strand}'"
    @nucleotide_counts[strand] || 0
  end

  def validate_strand?(valid, acid, error)
    raise ArgumentError, error unless acid.chars.all? { |a| valid.include?(a) }
  end
end

class DNA < DEOXYRIBONUCLEIC
  def initialize(strand)
    @acids = %w{ A C G T }
    super
  end
end

class RNA < DEOXYRIBONUCLEIC
  def initialize(strand)
    @acids = %w{ A C G U }
    super
  end
end

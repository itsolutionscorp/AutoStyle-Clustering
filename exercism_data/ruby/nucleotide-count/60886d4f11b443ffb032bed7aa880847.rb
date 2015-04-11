class DEOXYRIBONUCLEIC
  attr_accessor :nucleotide_counts

  ACIDS = %w{ A C G T U }

  def initialize(strand)
    @strand = strand
    valid_strand? @acids, @strand, "Invalid sequence '#{strand}'"

    @nucleotide_counts = @acids.each_with_object(Hash.new()) do |acid, values| 
      values[acid] ||= 0
    end
    @strand.chars.each { |acid| @nucleotide_counts[acid] += 1 }
  end

  def count(strand)
    valid_strand? ACIDS, strand, "Invalid strand '#{strand}'"
    @nucleotide_counts[strand] || 0
  end

  def valid_strand?(valid_acids, acid, error)
    acid.chars.each do |a|
      raise ArgumentError, error unless valid_acids.include?(a)
    end
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

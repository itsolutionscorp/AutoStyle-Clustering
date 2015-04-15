class DEOXYRIBONUCLEIC
  ACIDS = %w{ A C G T U }

  def initialize(strand)
    @strand = strand
    valid_strand? @acids, @strand

    @nucleotides = @acids.each_with_object(Hash.new()) { |acid, values| values[acid] ||= 0 }
    @strand.chars.each { |acid| @nucleotides[acid] += 1 }
  end

  def count(strand)
    valid_strand? ACIDS, strand
    @nucleotides[strand] || 0
  end

  def nucleotide_counts
    @nucleotides
  end

  def valid_strand?(valid_acids, acid)
    acid.chars.each do |a|
      raise ArgumentError unless valid_acids.include?(a)
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

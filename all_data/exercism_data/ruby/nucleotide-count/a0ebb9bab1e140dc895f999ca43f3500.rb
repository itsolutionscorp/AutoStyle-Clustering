class NucleicAcid
  NUCLEOTIDES = %w{ A C G T U }

  def initialize(nucleotides)
    @nucleotides = nucleotides
    validate_nucleotide_string!
  end

  def count(nucleotide)
    raise ArgumentError, "not a nucleotide: #{nucleotide}" unless NUCLEOTIDES.include? nucleotide
    unfiltered_nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    unfiltered_nucleotide_counts.reject{ |nucleotide, count|
      disabled_nucleotides.include? nucleotide
    }
  end

  def disabled_nucleotides
    []
  end

  private

  def unfiltered_nucleotide_counts
    @nucleotide_counts ||= count_nucleotides
  end

  def validate_nucleotide_string!
    non_nucleotides = @nucleotides.chars.uniq - (NUCLEOTIDES - disabled_nucleotides)
    raise ArgumentError, "invalid nucleotides: #{non_nucleotides.join(', ')}" unless non_nucleotides.empty?
  end

  def count_nucleotides
    counts = {}
    NUCLEOTIDES.each do |nucleotide|
      counts[nucleotide] = 0
    end
    @nucleotides.chars.each do |nucleotide|
      counts[nucleotide] += 1
    end
    counts
  end

end

class DNA < NucleicAcid

  def disabled_nucleotides
    %w{ U }
  end

end

class RNA < NucleicAcid

  def disabled_nucleotides
    %w{ T }
  end

end

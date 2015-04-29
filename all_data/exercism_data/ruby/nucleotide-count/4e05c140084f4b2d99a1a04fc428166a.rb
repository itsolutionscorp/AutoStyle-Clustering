class DEOXYRIBONECLEIC
  def initialize(strand)
    @molecules = %w{A C G T U}

    @strands = strand.chars
    @strands.each do |s|
      raise ArgumentError unless %w{A C G T U}.include?(s) && s != @exclude
    end

    @nucleotides = @molecules.inject({}) do |result, nucleotide|
      result[nucleotide] ||= 0
      result
    end

    @strands.each { |s| @nucleotides[s] += 1 }
  end

  def count(protein)
    raise ArgumentError unless %w{A C G T U}.include?(protein)
    @nucleotides[protein]
  end

  def nucleotide_counts
    @nucleotides.select { |nucleotide, _| nucleotide != @exclude }
  end
end

class DNA < DEOXYRIBONECLEIC
  def initialize(strand)
    @exclude = "U"
    super
  end

end

class RNA < DEOXYRIBONECLEIC
  def initialize(strand)
    @exclude = "T"
    super
  end
end

class DNA

  def initialize(s)
    if (s.count "^ACGT") != 0  then
      raise ArgumentError
    end
    @ns = s
  end

  def nucleotide_counts
    { 'A' => count('A'),
      'C' => count('C'),
      'G' => count('G'),
      'T' => count('T')
    }
  end

  def count(nuc)
    if nuc == 'X' then
      raise ArgumentError
    end
    return @ns.count(nuc)
  end

end

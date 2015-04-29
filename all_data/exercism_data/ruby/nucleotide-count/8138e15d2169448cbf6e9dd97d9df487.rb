class DNA
  NUCLEOTIDES = %w{A T C G U}

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @strand.count(nucleotide)
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G')
    }
  end
end

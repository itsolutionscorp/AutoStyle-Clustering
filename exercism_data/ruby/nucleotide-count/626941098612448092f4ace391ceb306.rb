class DNA
  NUCLEOTIDES = "ACGTU"

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    if NUCLEOTIDES.include? nucleotide
      @sequence.count nucleotide
    else
      raise ArgumentError
    end
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

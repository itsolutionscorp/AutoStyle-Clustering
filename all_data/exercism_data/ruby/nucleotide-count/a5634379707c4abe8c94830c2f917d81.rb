class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide) 
    raise ArgumentError, "Invalid nucleotide" unless valid?(nucleotide)
    strand.count(nucleotide) || 0
  end

  def valid?(nucleotide)
    ["A", "T", "C", "G", "U"].include?(nucleotide)
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

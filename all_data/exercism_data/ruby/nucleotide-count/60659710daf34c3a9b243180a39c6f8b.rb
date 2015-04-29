class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide) 
    raise ArgumentError, "Invalid nucleotide: " unless valid?(nucleotide)
    strand.count(nucleotide)
  end

  def valid?(nucleotide)
    ["A", "T", "C", "G", "U"].include?(nucleotide)
  end

  def nucleotide_counts
    {
      'A' => strand.count('A'),
      'T' => strand.count('T'),
      'C' => strand.count('C'),
      'G' => strand.count('G')
    }
  end
end

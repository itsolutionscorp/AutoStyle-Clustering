class DNA
  attr_reader :strand

  INVALID_NUCLEOTIDE = /[^GCTAU]/

  def invalid?(input)
    INVALID_NUCLEOTIDE =~ input
  end

  def initialize(strand)
    raise ArgumentError if invalid?(strand)
    @strand = strand
  end

  def count(nucleotide)
    raise ArgumentError if invalid?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    strand.split('').each do |nucleotide|
      counts[nucleotide] += 1
    end
    counts
  end
end

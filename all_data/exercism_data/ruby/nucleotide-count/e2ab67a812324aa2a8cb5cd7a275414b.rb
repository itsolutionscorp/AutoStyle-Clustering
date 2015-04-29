class DNA
  attr_reader :strand
  def invalid_nucleotide
    /[^GCTAU]/
  end

  def check_input(input)
    raise ArgumentError if invalid_nucleotide =~ input
  end

  def initialize(strand)
    check_input(strand)
    @strand = strand
  end

  def count(nucleotide)
    check_input(nucleotide)
    self.nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    self.strand.split('').each do |nucleotide|
      counts[nucleotide] += 1
    end
    counts
  end
end

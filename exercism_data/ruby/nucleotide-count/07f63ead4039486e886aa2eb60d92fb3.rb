class DNA

  def initialize(strand)
    raise ArgumentError unless strand =~ /^[ATCG]*$/
    @strand = strand
  end

  def count(nucleotide)
    raise ArgumentError unless %w{A T C G U}.include?(nucleotide)
    strand.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides.reduce(empty_counts) { |acc, c| acc.merge(c => acc[c] + 1) }
  end

  private

  def nucleotides
    strand.chars
  end

  def empty_counts
    { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
  end

  attr_reader :strand
end

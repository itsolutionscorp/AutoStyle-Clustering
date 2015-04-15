class DNA
  attr_reader :strand

  VALID_REGEX = /^[ATCG]*$/
  EMPTY_STRAND = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }

  def initialize(strand)
    self.strand = strand
  end

  def strand=(other)
    @strand = other.chars
    valid?
    @nucleotide_counts = nil
  end

  def count(nucleotide)
    fail ArgumentError unless EMPTY_STRAND.key?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @nucleotide_counts ||= strand.each_with_object(EMPTY_STRAND.clone) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  def valid?
    fail ArgumentError unless VALID_REGEX.match(strand.join)
  end
end

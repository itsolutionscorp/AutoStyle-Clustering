class DNA
  def initialize sequence
    @sequence = sequence
  end

  def count nucleotide
    validate nucleotide
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    nucleotides.each_with_object(counts) {|nucleotide, counts| counts[nucleotide] += 1 }
  end

  private

  def nucleotides
    @sequence.each_char
  end

  def validate nucleotide
    raise ArgumentError unless VALID_NUCLEOTIDES.include? nucleotide
  end

  VALID_NUCLEOTIDES = ['A', 'C', 'G', 'T', 'U']
end

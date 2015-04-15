class DNA
  NUCLEOTIDES = 'ATCGU'
  def initialize(sequence)
    @sequence = sequence.chars
    @nucleotide_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @nucleotide_counts.default = 0
    @nucleotide_counts.keys.each do |nucleotide|
      @nucleotide_counts[nucleotide] = @sequence.count(nucleotide)
    end
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @nucleotide_counts[nucleotide]
  end

end

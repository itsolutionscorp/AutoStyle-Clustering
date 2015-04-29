class DNA
  attr_reader :dna

  VALID_NUCLEOTIDES = "ATCGU".freeze

  def initialize(dna)
    @dna = dna
  end

  def count(nucleotide)
    raise ArgumentError unless VALID_NUCLEOTIDES.include? nucleotide
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    @nucleotides ||= @dna.chars.each_with_object(initial_count) { |nucleotide, count| count[nucleotide] += 1 }
  end

  def initial_count
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

end

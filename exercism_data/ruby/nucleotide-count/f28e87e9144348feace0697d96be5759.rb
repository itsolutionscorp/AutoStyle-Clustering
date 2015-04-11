class DNA
  attr_reader :nucleotide_counts
  RNA_NUCLEOTIDES = 'ACGU'.chars
  DNA_NUCLEOTIDES = 'ATCG'.chars

  NUCLEOTIDES = RNA_NUCLEOTIDES | DNA_NUCLEOTIDES

  def initialize(sequence)
    @nucleotide_counts = DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, histogram|
      histogram[nucleotide] = sequence.count(nucleotide)
    end
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    nucleotide_counts[nucleotide]
  end

end

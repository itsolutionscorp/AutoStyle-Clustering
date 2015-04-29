class DNA

  VALID_NUCLEOTIDES = ['A', 'T', 'C', 'G']

  attr_reader :sequence

  def initialize(nucleotides)
    @sequence = nucleotides.chars
    raise ArgumentError if sequence.any? {|elem| invalid? elem}
  end

  def count(nucleotide)
    raise ArgumentError if invalid?(nucleotide)
    sequence.count(nucleotide)
  end

  def invalid?(nucleotide)
    !VALID_NUCLEOTIDES.include? nucleotide
  end

  def nucleotide_counts
    VALID_NUCLEOTIDES.each_with_object({}) { |nucleotide, counts| counts[nucleotide] = count(nucleotide)}
  end

end

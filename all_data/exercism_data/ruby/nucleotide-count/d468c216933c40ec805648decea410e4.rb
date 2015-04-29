class DNA

  VALID_NUCLEOTIDES = %w(A T C G)

  def initialize(sequence)
    empty_census = Hash[ VALID_NUCLEOTIDES.zip([0] * VALID_NUCLEOTIDES.length) ]
    @census = sequence.split('').each_with_object(empty_census) do |letter, census|
      raise ArgumentError unless VALID_NUCLEOTIDES.include? letter
      census[letter] += 1
    end
  end

  def count(nucleotide)
    @census[nucleotide] or raise ArgumentError
  end

  def nucleotide_counts
    @census
  end

end

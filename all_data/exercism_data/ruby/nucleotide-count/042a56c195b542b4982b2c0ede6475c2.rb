class DNA
  GUANINE = 'G'
  ADENINE = 'A'
  THYAMINE = 'T'
  CYTOSINE = 'C'
  URACIL = 'U'

  NUCLEOTIDES = [GUANINE, ADENINE, THYAMINE, CYTOSINE, URACIL]
  DNA_NUCLEOTIDES = NUCLEOTIDES - [URACIL]

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) { |nucleotide, counter| counter[nucleotide] = count(nucleotide) }
  end

  private

  attr_reader :sequence
end

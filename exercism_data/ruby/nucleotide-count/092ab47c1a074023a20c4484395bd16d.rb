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
    {
      ADENINE => count(ADENINE),
      GUANINE => count(GUANINE),
      THYAMINE => count(THYAMINE),
      CYTOSINE => count(CYTOSINE)
    }
  end

  private

  attr_reader :sequence
end

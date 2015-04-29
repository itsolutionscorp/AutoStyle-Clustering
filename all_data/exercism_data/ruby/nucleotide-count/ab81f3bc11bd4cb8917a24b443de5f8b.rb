module Nucleotids
  ADENINE  = 'A'
  GUANINE  = 'G'
  CYTIDINE = 'C'
  THYMINE  = 'T'
  URACIL   =  'U'

  def valid?(nucleotide)
    [ADENINE, GUANINE, CYTIDINE, THYMINE, URACIL].include? nucleotide
  end
end

class DNA
  include Nucleotids

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless valid?(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    {
      ADENINE  => count(ADENINE),
      GUANINE  => count(GUANINE),
      CYTIDINE => count(CYTIDINE),
      THYMINE  => count(THYMINE)
    }
  end
end

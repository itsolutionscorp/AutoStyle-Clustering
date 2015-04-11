class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def count(letter)
    raise ArgumentError unless valid_base?(letter)
    _count(letter)
  end

  def nucleotide_counts
    Nucleotides::DNA_BASES.each_with_object({}) {|base, counts|
      counts[base] = _count(base)
    }
  end

  private
  def _count(letter)
    strand.count(letter)
  end

  def valid_base?(letter)
    Nucleotides::BASES.include?(letter)
  end

end

module Nucleotides
  GUANINE  = 'G'
  CYTOSINE = 'C'
  ADENINE  = 'A'
  THYMINE  = 'T'
  URACIL   = 'U'

  COMMON_BASES = [GUANINE, CYTOSINE, ADENINE]

  BASES        = COMMON_BASES + [THYMINE] + [URACIL]
  DNA_BASES    = COMMON_BASES + [THYMINE]
  RNA_BASES    = COMMON_BASES             + [URACIL]
end

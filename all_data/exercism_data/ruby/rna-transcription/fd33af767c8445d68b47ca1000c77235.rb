# I'm not certain I'd create the Nucleotides module to
# solve *this* problem, but since I have it lying around
# from ruby/nucleotide-count...
#

class DNA
  attr_reader :strand
  def initialize(strand)
    @strand = strand
  end

  def to_rna
    strand.tr(Nucleotides::THYMINE, Nucleotides::URACIL) end
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

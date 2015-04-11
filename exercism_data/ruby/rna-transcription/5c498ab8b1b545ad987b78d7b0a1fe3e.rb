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
end

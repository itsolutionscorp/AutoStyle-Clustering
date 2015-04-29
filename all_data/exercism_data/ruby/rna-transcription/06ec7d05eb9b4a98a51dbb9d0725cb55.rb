class DNA
  THYMINE = "T"
  URACIL  = "U"

  def initialize(strand)
    @dna_strand = strand
  end

  def to_rna
    @dna_strand.tr(THYMINE, URACIL)
  end
end

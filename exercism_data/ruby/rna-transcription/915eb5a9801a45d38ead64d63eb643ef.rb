class DNA
  URACIL  = "U"
  THYMINE = "T"

  def initialize(nucleotides)
    @strand = nucleotides
  end

  def to_rna
    @strand.tr(THYMINE, URACIL)
  end
end

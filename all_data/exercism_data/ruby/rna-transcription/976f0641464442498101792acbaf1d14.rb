class DNA
  ADENINE = ?A
  CYTOSINE = ?C
  GUANINE = ?G
  THYMINE = ?T
  URACIL = ?U

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    @strand.tr(THYMINE, URACIL)
  end
end

class DNA

  THYMINE = 'tT'
  URACIL = 'uU'

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    @strand.tr(THYMINE, URACIL)
  end

end

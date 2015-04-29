class DNA
  THYMINE = 'T'
  URACIL = 'U'
  
  def initialize(strand)
    @strand = strand
  end

  def to_rna
    @strand.tr(THYMINE, URACIL)
  end
end

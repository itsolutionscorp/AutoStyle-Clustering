class DNA
   URACIL = "U" 
   THYMINE = "T"
  attr_reader :strand

  def initialize(strand)
    @strand = strand.to_s
  end

  def to_rna
    strand.tr(THYMINE, URACIL)
  end
end

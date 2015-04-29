class DNA

  attr_reader :strand

  THYMAINE = "T"
  URACIL = "U"

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    strand.tr(THYMAINE, URACIL)
  end

end 

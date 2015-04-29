class DNA
  THYMIDINE = "T"
  URACIL    = "U"

  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    convert_thymidines_to_uracils
  end

  def convert_thymidines_to_uracils
    strand.tr(THYMIDINE, URACIL)
  end
end

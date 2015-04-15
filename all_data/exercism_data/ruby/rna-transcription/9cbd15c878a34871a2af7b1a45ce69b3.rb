class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    strand.tr(thymidine, uracil)
  end

  def thymidine
    "T"
  end

  def uracil
    "U"
  end
end

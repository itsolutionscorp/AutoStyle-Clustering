class DNA
  THYMIDINE = "T"
  URACIL = "U"

  def initialize (genes)
    @genes = genes
  end

  def to_rna
    @genes.gsub(THYMIDINE, URACIL)
  end
end

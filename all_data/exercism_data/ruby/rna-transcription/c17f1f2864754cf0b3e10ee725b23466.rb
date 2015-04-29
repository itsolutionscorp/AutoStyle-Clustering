class DNA
  @@thymidine = "T"
  @@uracil = "U"

  def initialize (genes)
    @genes = genes
  end

  def to_rna
    @genes.gsub(@@thymidine, @@uracil)
  end
end

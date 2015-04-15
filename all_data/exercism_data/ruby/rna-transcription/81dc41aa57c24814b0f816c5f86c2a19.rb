class DNA

  attr_reader :strand

  @@thymaine = "T"
  @@uracil = "U"

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    strand.tr(@@thymaine, @@uracil)
  end

end 

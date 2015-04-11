class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand.to_s
  end

  def to_rna
    strand.tr("T","U")
  end
end

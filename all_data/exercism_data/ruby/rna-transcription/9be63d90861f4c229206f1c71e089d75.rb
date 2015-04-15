class DNA < String
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    thymidine_to_uracil(strand)
  end

  private
  def thymidine_to_uracil(strand)
    strand.tr('T', 'U')
  end
end

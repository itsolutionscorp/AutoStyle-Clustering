class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand.upcase
  end

  def to_rna
    strand.tr('T','U')
  end

end

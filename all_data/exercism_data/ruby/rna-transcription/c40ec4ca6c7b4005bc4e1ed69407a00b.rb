class DNA

  attr_accessor :strand

  def initialize string
    @strand = string
  end

  def to_rna
    strand.tr('T','U')
  end

end

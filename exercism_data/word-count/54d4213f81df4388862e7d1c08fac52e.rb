class DNA

  def initialize(strand)
    @strand = strand.to_s
  end

  def to_rna
    @strand.gsub(/T/,'U')
  end
end

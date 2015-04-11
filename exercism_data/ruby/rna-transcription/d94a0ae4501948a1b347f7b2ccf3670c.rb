class DNA
  def initialize(strand)
    @strand = strand
  end

  def to_rna
    @strand.tr 'T', 'U'
  end
end

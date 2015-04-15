class DNA

  def initialize(strand)
    @dna_strand = strand
  end

  def to_rna
    swap_thymine_uracil
  end

  private
  def swap_thymine_uracil
    @dna_strand.tr("T", "U")
  end

end

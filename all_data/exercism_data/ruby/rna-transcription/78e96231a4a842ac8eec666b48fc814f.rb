class DNA

  def initialize(strand)
    @dna_strand = strand
  end

  def to_rna
    @dna_strand.gsub("T", "U")
  end

end

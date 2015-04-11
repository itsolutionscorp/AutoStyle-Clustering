class DNA
  THYMINE = 'T'
  URACIL = 'U'
  
  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def to_rna
    @dna_strand.tr(THYMINE, URACIL)
  end
end

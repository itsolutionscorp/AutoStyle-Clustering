class DNA
  THYMINE = "T"
  URACIL = "U"

  def initialize(dna_strand)
    @strand = dna_strand
  end

  def to_rna
    strand.tr(THYMINE, URACIL)
  end

  private

  attr_reader :strand
end

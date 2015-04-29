class DNA

  attr_reader :dna_strand

  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def to_rna
    dna_strand.gsub(/["T"]/, "U")
  end
end

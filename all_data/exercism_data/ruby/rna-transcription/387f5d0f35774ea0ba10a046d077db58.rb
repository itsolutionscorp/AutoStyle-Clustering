class DNA
  def initialize(dna_strand)
    @dna_strand = dna_strand.upcase
  end

  def to_rna
    @dna_strand.tr('T','U')
  end
end

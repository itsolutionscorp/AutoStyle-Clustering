class DNA
  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @dna.tr("ACGT", "ACGU")
  end
end

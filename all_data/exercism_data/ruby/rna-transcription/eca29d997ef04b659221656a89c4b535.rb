class DNA
  def initialize(dna)
    @dna = String(dna)
  end

  def to_rna
    @dna.tr("ACGT", "ACGU")
  end
end

class DNA
  def initialize(genome)
    @genome = genome
  end

  def to_rna
    @genome.tr("T", "U")
  end
end

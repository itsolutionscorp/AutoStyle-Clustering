class DNA
  Adenine  = "A"
  Cytosine = "C"
  Guanine  = "G"
  Thymine  = "T"
  Uracil   = "U"

  def initialize(genome)
    @genome = genome
  end

  def to_rna
    @genome.tr(Adenine,  Adenine)
    @genome.tr(Cytosine, Cytosine)
    @genome.tr(Guanine,  Guanine)
    @genome.tr(Thymine,  Uracil)
  end
end

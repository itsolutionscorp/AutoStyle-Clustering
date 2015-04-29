class DNA

  DNA_T = 'tT'
  RNA_U = 'uU'

  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @dna.tr(DNA_T, RNA_U)
  end

end

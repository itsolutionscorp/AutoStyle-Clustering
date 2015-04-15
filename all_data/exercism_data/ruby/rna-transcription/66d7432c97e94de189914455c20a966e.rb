class DNA
  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @dna.gsub(thymine, uracil)
  end

protected
  def thymine
    'T'
  end

  def uracil
    'U'
  end
end

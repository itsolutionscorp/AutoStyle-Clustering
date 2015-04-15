class DNA
  attr_reader :dna
  
  def initialize(dna)
    @dna = dna
  end
  
  def to_rna
    dna.tr(thymine, uracil)
  end
  
  private
  def uracil
    'U'
  end
  
  def thymine
    'T'
  end
end

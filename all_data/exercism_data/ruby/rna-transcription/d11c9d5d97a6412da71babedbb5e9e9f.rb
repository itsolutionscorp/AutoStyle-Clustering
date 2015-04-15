class DNA
  attr_reader :dna
  THYMIDINE = 'T'
  URACIL = 'U'


  def initialize(dna)
    @dna = dna
  end
  
  def to_rna
    dna.tr THYMIDINE, URACIL
  end
  
end

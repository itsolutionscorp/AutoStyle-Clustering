module Nucleotides
  ADENOSINE = 'A'
  CYTIDINE  = 'C'
  GUANOSINE = 'G'
  THYMIDINE = 'T'
  URACIL    = 'U'
end

class DNA
  attr_reader :sequence
  
  def initialize(sequence)
    @sequence = sequence
  end
  
  def to_rna
    sequence.tr(thymidine, uracil)
  end
  
  private
  
  def thymidine
    Nucleotides::THYMIDINE
  end
  
  def uracil
    Nucleotides::URACIL
  end
end

class DNA
  
  THYMIDINE = 'T'
  URACIL = 'U'

  attr_reader :sequence
  
  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    sequence.tr(DNA::THYMIDINE, DNA::URACIL)
  end
end

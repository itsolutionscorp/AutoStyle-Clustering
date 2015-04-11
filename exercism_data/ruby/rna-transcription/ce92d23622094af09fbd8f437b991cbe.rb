class DNA
  THYMIDINE = 'T'
  URACIL = 'U'

  attr_accessor :sequence

  def initialize(sequence)
    self.sequence = sequence
  end

  def to_rna
    sequence.tr THYMIDINE, URACIL
  end
end

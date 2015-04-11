class DNA
  THYMIDINE = 'T'
  URIDINE   = 'U'

  attr_accessor :sequence

  def initialize(sequence)
    self.sequence = sequence
  end

  def to_rna
    sequence.gsub(THYMIDINE, URIDINE)
  end
end

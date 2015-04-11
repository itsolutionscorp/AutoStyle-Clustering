class DNA
  attr_reader :sequence

  THYMIDINE = 'T'
  URIDINE = 'U'

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    sequence.gsub(THYMIDINE, URIDINE)
  end
end

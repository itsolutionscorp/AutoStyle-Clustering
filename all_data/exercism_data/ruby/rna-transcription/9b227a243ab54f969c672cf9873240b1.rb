class DNA
  THYMINE = 'T'
  URACIL  = 'U'

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    sequence.tr(THYMINE, URACIL)
  end
end

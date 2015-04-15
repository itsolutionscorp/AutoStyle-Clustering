class DNA

  THYMIDINE = "T"
  URACIL = "U"

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    sequence.tr(THYMIDINE, URACIL)
  end

  private

  attr_reader :sequence

end

module StrandUnits
  THYMIDINE = 'T'
  URACIL = 'U'
end


class DNA

  include StrandUnits

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    @sequence.tr(THYMIDINE, URACIL)
  end

end

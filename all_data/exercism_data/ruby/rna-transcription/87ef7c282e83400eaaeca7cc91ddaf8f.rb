class DNA < Struct.new(:sequence)
  THYMIDINE = 'T'
  URACIL = 'U'

  def to_rna
    sequence.tr(THYMIDINE, URACIL)
  end
end

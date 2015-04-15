class DNA < String
  THYMIDINE = 'T'.freeze
  URACIL    = 'U'.freeze

  def to_rna
    tr(THYMIDINE, URACIL)
  end
end

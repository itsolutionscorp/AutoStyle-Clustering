DNA = Struct.new(:strand) do
  THYMIDINE = 'T'.freeze
  URACIL    = 'U'.freeze

  def to_rna
    strand.tr(THYMIDINE, URACIL)
  end
end

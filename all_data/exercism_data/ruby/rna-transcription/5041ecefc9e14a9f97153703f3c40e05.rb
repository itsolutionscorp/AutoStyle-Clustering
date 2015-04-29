DNA = Struct.new(:strand) do
  THYMINE = "T"
  URACIL = "U"

  def to_rna
    strand.gsub(THYMINE, URACIL)
  end
end

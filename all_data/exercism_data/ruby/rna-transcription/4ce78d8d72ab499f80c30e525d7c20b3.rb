DNA = Struct.new(:segment) do

  URACIL = "U"
  THYMINE = "T"

  def to_rna
    segment.gsub(THYMINE, URACIL)
  end

end

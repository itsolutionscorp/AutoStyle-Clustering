DNA = Struct.new(:dna_segment) do

  URACIL = "U"
  THYMINE = "T"

  def to_rna
    dna_segment.gsub(THYMINE, URACIL)
  end

end

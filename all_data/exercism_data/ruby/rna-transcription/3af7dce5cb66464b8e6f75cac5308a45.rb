DNA = Struct.new(:dna_segment) do

  Uracil = "U"
  Thymine = "T"

  def to_rna
    dna_segment.gsub(Thymine, Uracil)
  end

end

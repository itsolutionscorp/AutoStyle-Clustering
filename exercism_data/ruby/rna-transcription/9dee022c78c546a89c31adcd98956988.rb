class DNA < Struct.new(:sequence)
  THYMIDINE = "T"
  URACIL    = "U"

  def to_rna
    sequence.gsub(THYMIDINE, URACIL)
  end

end

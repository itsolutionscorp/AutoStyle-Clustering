DNA = Struct.new(:code) do
  THYMIDINE = "T"
  URACIL = "U"

  def to_rna
    code.tr THYMIDINE, URACIL
  end
end

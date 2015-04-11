DNA = Struct.new(:code) do
  def to_rna
    code.tr "T", "U"
  end
end

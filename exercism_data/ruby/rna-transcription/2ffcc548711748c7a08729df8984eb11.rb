class DNA < Struct.new(:code)
  def to_rna
    code.tr("T", "U")
  end
end

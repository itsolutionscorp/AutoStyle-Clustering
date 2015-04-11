class DNA < Struct.new(:string)
  def to_rna
    string.gsub("T", "U")
  end
end

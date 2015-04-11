DNA = Struct.new(:sequence) do
  def to_rna
    sequence.gsub("T", "U")
  end
end

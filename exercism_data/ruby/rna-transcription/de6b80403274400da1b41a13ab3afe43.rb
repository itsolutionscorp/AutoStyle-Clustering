DNA = Struct.new(:dna) do
  def to_rna
    dna.gsub("T", "U")
  end
end

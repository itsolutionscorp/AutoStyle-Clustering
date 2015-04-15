DNA = Struct.new(:dna) do
  def to_rna
    dna.tr('T', 'U')
  end
end

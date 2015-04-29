DNA = Struct.new(:rna) do
  def to_rna
    rna.tr 'T', 'U'
  end
end

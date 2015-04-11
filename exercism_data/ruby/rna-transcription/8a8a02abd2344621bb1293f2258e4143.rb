DNA = Struct.new(:strand) do
  def to_rna
    strand.tr('T', 'U')
  end
end

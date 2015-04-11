DNA = Struct.new(:sequence) do
  THYMINE = 'T'
  URACIL = 'U'
  def to_rna
    sequence.tr(THYMINE, URACIL)
  end
end

class DNA < Struct.new(:strand)
  THYMINE = 'T'
  URACIL  = 'U'

  def to_rna
    strand.tr(THYMINE, URACIL)
  end
end

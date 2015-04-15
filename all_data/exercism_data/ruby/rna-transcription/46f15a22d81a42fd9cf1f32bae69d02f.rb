class DNA < Struct.new(:strand)
  THYMINE = 'T'
  URACIL  = 'U'

  def to_rna
    strand.gsub(THYMINE, URACIL)
  end
end

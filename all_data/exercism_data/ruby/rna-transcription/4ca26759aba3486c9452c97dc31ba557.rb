class DNA < Struct.new(:dna_string)
  THYMINE = 'T'
  URACIL  = 'U'

  def to_rna
    dna_string.gsub(THYMINE, URACIL)
  end
end

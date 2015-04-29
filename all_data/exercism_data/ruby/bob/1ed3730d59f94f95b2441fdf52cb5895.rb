class DNA < Struct.new(:strand)

  URACIL = 'U'
  THYMIDINE = 'T'

  def to_rna
    strand.gsub(THYMIDINE, URACIL)
  end

end

class DNA < Struct.new(:dna_string)

  URACIL = 'U'

  def to_rna
    dna_string.gsub(/T/, URACIL)
  end

end

class RibonucleicAcid < String
  def initialize(code)
    super code
  end

end

class DeoxyribonucleicAcid < String

  THYMIDINE = 'T'
  URACIL = 'U'

  def initialize(code)
    super code
  end

  def to_rna
    rna_code = gsub(THYMIDINE, URACIL)
    RibonucleicAcid.new(rna_code)
  end
end

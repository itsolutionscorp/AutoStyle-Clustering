class RibonucleicAcid < String
  def initialize(code)
    super code
  end

end

class DeoxyribonucleicAcid < String

  def initialize(code)
    super code
  end

  def to_rna
    rna_code = gsub('T', 'U')
    RibonucleicAcid.new(rna_code)
  end
end

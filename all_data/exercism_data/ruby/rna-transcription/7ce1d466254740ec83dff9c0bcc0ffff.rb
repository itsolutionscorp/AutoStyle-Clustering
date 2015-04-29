class NucleicAcid < String

  def initialize(code)
    @code = code
    super
  end

  def to_s
    @code
  end

end

class RibonucleicAcid < NucleicAcid

  def to_rna
    DeoxyribonucleicAcid.new(@code.gsub('U', 'T'))
  end

end

class DeoxyribonucleicAcid < NucleicAcid

  def to_rna
    RibonucleicAcid.new(@code.gsub('T', 'U'))
  end

end

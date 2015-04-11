module StringComparable
  def ==(other)
    if other.respond_to? :to_str
      to_str == other
    else
      super.==(other)
    end
  end
end

class Acid
  include StringComparable

  def initialize(code)
    @code = code
  end

  def to_str
    @code
  end
end

class RibonucleicAcid < Acid
  URACIL = 'U'

  def self.from_dna(code)
    new(code.gsub(DeoxyribonucleicAcid::THYMIDINE, URACIL))
  end

end

class DeoxyribonucleicAcid < Acid
  THYMIDINE = 'T'

  def initialize(code)
    super(code)
    @rna = RibonucleicAcid.from_dna(code)
  end

  def to_rna
    @rna
  end

end

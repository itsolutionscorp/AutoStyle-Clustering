module StringComparable
  def ==(other)
    if other.respond_to? :to_str
      to_str == other
    else
      super.==(other)
    end
  end
end

class RibonucleicAcid

  THYMIDINE = 'T'
  URACIL = 'U'

  include StringComparable

  def initialize(code)
    @code = code
  end

  def self.from_dna(code)
    new(code.gsub(THYMIDINE, URACIL))
  end
  
  def to_str
    @code
  end

end

class DeoxyribonucleicAcid
  
  include StringComparable

  def initialize(code)
    @code = code
    @rna = RibonucleicAcid.from_dna(code)
  end

  def to_rna
    @rna
  end

  def to_str
    @code
  end
end

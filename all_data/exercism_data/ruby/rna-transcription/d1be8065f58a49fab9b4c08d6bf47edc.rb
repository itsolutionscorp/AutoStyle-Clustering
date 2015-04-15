class RibonucleicAcid
  def initialize(string)
    @string = string
  end
  def to_s
    @string
  end
  def ==(object)
    object.to_s == @string
  end
end

class String
  alias :old_equals :==

  def ==(that)
    if that.kind_of?(RibonucleicAcid)
      that == self
    else
      old_equals(that)
    end
  end
end

class DeoxyribonucleicAcid < RibonucleicAcid

  def to_rna
    RibonucleicAcid.new(@string.gsub("T","U"))
  end
end

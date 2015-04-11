class String
  def ==(other)
    return other == self if other.is_a?(RibonucleicAcid)
    super(other)
  end
end

class RibonucleicAcid
  def initialize(string)
    @string = string
  end

  def ==(other)
    other == @string
  end
end

class DeoxyribonucleicAcid
  def initialize(string)
    @string = string
  end

  def ==(other)
    other == @string
  end

  def to_rna
    RibonucleicAcid.new(rna_string)
  end

  private

  def rna_string
    @string.gsub('T', 'U')
  end
end

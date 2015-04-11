class Acid
  def initialize(string)
    @string = string
  end

  def ==(other)
    other == @string
  end

  def to_str
    @string
  end
end

class RibonucleicAcid < Acid; end

class DeoxyribonucleicAcid < Acid
  def to_rna
    RibonucleicAcid.new(rna_string)
  end

  private

  def rna_string
    @string.gsub('T', 'U')
  end
end

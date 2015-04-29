class DNA
  def initialize(string)
    @string = string
  end

  def to_rna
    @string.tr("T", "U")
  end
end

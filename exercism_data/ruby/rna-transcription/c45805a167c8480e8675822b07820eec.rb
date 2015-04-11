class DNA
  attr_reader :code

  def initialize(code)
    @code = code
  end

  def to_rna
    code.tr("T", "U")
  end
end

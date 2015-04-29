class DNA

  attr_reader :code
  def initialize(code)
    @code = code
  end
  
  def to_rna
    code.gsub('T', 'U')
  end
end

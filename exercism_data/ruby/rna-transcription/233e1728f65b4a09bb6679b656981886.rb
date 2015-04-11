class DNA
  attr_accessor :code
  
  def initialize(code)
    @code = code
  end
  
  def to_rna
    @code.gsub("T", "U")
  end
end

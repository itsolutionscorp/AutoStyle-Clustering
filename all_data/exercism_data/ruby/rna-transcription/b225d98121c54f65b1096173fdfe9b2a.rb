class DNA

  def initialize(string)
    @string = string
  end

  def to_rna
    @string.to_s.gsub("T", "U")
  end
  
end

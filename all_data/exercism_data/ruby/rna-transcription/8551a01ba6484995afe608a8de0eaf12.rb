class DNA
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def to_rna
    string.gsub("T", "U")
  end
end

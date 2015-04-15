class DNA
  def initialize(string)
    @string = string
  end

  def to_rna
    uracil = 'U'
    thymine = 'T'
    @string.gsub(uracil, thymine)
  end
end

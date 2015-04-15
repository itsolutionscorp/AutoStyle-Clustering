class DNA
  attr_reader :dna_string

  def initialize(input)
    @dna_string = input
  end

  def to_rna
    dna_string.gsub("T", "U")
  end
end

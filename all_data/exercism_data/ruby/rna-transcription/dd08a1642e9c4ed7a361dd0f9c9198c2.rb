class DNA
  attr_reader :dna

  def initialize(input)
    @dna = input
  end

  def to_rna
    dna.gsub("T", "U")
  end
end

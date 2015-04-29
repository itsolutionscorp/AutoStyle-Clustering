class DNA
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.gsub("T", "U")
  end
end

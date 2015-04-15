class DNA
  def initialize(dna_data)
    @dna_data = dna_data
  end

  def to_rna
    @dna_data.gsub("T", "U")
  end
end

class DNA
  
  NUCLEOTIDES = { adenine: "A", cytosine: "C", guanine: "G", thymine: "T", uracil: "U" }
  
  def initialize(nucleotides)
    raise ArgumentError if invalid(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.gsub NUCLEOTIDES[:thymine], NUCLEOTIDES[:uracil]
  end
  
  private
  
  def invalid(nucleotides)
    nucleotides.empty? || !nucleotides.is_a?(String)
  end

end

class DNA
  
  NUCLEOTIDES = { adenine: "A", cytosine: "C", guanine: "G", thymine: "T", uracil: "U" }
  
  def initialize(sequence)
    raise ArgumentError if invalid(sequence)
    @strand = sequence
  end

  def to_rna
    @strand.gsub NUCLEOTIDES[:thymine], NUCLEOTIDES[:uracil]
  end
  
  private
  
  def invalid(sequence)
    sequence.empty? || !sequence.is_a?(String)
  end

end

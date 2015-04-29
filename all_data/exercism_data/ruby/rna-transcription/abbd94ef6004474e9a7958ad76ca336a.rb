class DNA
  NUCLEOTIDES = {thymine: 'T', uracil: 'U'}

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    @strand.tr NUCLEOTIDES[:thymine], NUCLEOTIDES[:uracil]
  end
end
